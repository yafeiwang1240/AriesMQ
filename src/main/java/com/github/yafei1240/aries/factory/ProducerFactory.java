package com.github.yafei1240.aries.factory;

import com.github.yafei1240.aries.client.Producer;
import com.github.yafei1240.aries.client.impl.AriesProducer;

/**
 * 生产者工厂类
 * @author wangyafei
 */
public class ProducerFactory {

    public static <K, V> Producer<K, V> newProducer() {
        return new AriesProducer<>();
    }
}
