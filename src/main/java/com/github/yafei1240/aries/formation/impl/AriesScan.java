package com.github.yafei1240.aries.formation.impl;

import com.github.yafei1240.aries.factory.AriesWorkerFactory;
import com.github.yafei1240.aries.factory.ThreadPoolFactory;
import com.github.yafei1240.aries.formation.Scanning;
import com.github.yafei1240.aries.model.ManagerPack;
import com.github.yafei1240.aries.client.record.FutureRecord;
import com.github.yafei1240.aries.client.record.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AriesScan implements Scanning {

    private static Logger logger = LoggerFactory.getLogger(AriesScan.class);

    private Queue<FutureRecord> workerQueue;
    private Object[] lock;
    private ExecutorService service;
    private ExecutorService pool;
    private AriesScanWorker worker;

    public AriesScan() {
        workerQueue = new ArrayDeque<>();
        lock = new Object[0];
        service = ThreadPoolFactory.newSingleThreadExecutor();
        pool = ThreadPoolFactory.newThreadPoolExecutor();
        worker = new AriesScanWorker();
        service.execute(worker);
    }

    @Override
    public <T> Future<T> invoke(ManagerPack pack, ProducerRecord record, T result) {
        FutureRecord<T> future;
        synchronized (lock) {
            AriesWorker<T> worker = AriesWorkerFactory.newAriesWorker(pack, record, result);
            future = new FutureRecord(worker, result);
            workerQueue.add(future);
            lock.notify();
        }
        return future;
    }

    private class AriesScanWorker implements Runnable {
        private boolean exit;
        @Override
        public void run() {
            List<FutureRecord> records = new ArrayList<>();
            while (!exit) {
                synchronized (lock) {
                    while (!workerQueue.isEmpty()) {
                        records.add(workerQueue.poll());
                    }
                }
                records.stream().forEach(_value -> {
                    pool.execute(_value);
                });
                records.clear();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    logger.warn(e.getMessage(), e);
                }
            }
        }
        public void stop() {
            exit = true;
        }
    }
}
