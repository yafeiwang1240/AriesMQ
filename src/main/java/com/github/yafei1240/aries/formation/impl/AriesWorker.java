package com.github.yafei1240.aries.formation.impl;

import com.github.yafei1240.aries.common.SystemEnvironment;
import com.github.yafei1240.aries.model.ManagerPack;
import com.github.yafei1240.aries.client.record.CustomerRecord;
import com.github.yafei1240.aries.client.record.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangyafei
 */
public class AriesWorker<T> implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(AriesWorker.class);

    // 分发并发条件
    private static final int PARALLEL = Math.max(16 - SystemEnvironment.getCpuNum(), 4);

    private ManagerPack managerPack;

    private ProducerRecord producerRecord;

    private T result;

    @Override
    public void run() {
        try {
            CustomerRecord customerRecord = new CustomerRecord(producerRecord.topic(), producerRecord.key(), producerRecord.value(), producerRecord.timestamp());
            if (!managerPack.isProcess()) {
                // 随机
                managerPack.getSubject().notifyObserver(customerRecord);
            } else {
                // 并发优化
                if (managerPack.getSubject().getObservers().size() >= PARALLEL) {
                    managerPack.getSubject().notifyAllObserverParallel(customerRecord);
                } else {
                    // 串行
                    managerPack.getSubject().notifyAllObserver(customerRecord);
                }
            }
            managerPack.getCallBack().invoke(result, null);
        } catch (Exception e) {
            managerPack.getCallBack().invoke(result, e);
        }
    }

    public ManagerPack getManagerPack() {
        return managerPack;
    }

    public void setManagerPack(ManagerPack managerPack) {
        this.managerPack = managerPack;
    }

    public ProducerRecord getProducerRecord() {
        return producerRecord;
    }

    public void setProducerRecord(ProducerRecord producerRecord) {
        this.producerRecord = producerRecord;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
