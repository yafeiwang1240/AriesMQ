package com.github.yafei1240.aries.factory;

import com.github.yafei1240.aries.formation.impl.AriesWorker;
import com.github.yafei1240.aries.model.ManagerPack;
import com.github.yafei1240.aries.client.record.ProducerRecord;

/**
 * 实际工作线程工厂类
 *
 * @author wangyafei
 */
public class AriesWorkerFactory {
    public static <T> AriesWorker newAriesWorker(ManagerPack managerPack, ProducerRecord producerRecord, T result) {
        AriesWorker worker = new AriesWorker();
        worker.setManagerPack(managerPack);
        worker.setProducerRecord(producerRecord);
        worker.setResult(result);
        return worker;
    }
}
