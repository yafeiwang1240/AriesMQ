package com.github.yafei1240.aries.factory;

import com.github.yafei1240.aries.client.record.CustomerRecord;
import com.github.yafei1240.aries.client.Customer;
import com.github.yafei1240.aries.client.MessageConsumer;
import com.github.yafei1240.aries.client.impl.AriesCustomer;
import com.github.yafei1240.aries.exception.InvalidTopicExcepiton;
import com.github.yafei1240.aries.exception.NoSuchTopicException;

/**
 * 消费者工厂类
 * @author wangyafei
 */
public class CustomerFactory {
    public static <K, V> Customer<K, V> newCustomer(String topic, MessageConsumer<CustomerRecord<K, V>> consumer) throws NoSuchTopicException, InvalidTopicExcepiton {
        return new AriesCustomer<>(topic, consumer);
    }
}
