package com.github.yafei1240.aries.client.impl;

import com.github.yafei1240.aries.exception.InvalidTopicException;
import com.github.yafei1240.aries.exception.NoSuchTopicException;
import com.github.yafei1240.aries.observer.Observer;
import com.github.yafei1240.aries.client.record.CustomerRecord;
import com.github.yafei1240.aries.client.Customer;
import com.github.yafei1240.aries.client.MessageConsumer;
import com.github.yafei1240.aries.server.BrokerServer;

import java.io.IOException;

public class AriesCustomer<K, V> implements Customer<K, V>, Observer<CustomerRecord<K, V>> {

    private String register;
    private String topic;
    private MessageConsumer<CustomerRecord<K, V>> consumer;

    public AriesCustomer(String topic, MessageConsumer<CustomerRecord<K, V>> consumer) throws NoSuchTopicException, InvalidTopicException {
        this.topic = topic;
        this.consumer = consumer;
        newValidException();
        BrokerServer.register(this);
    }

    private void newValidException() throws InvalidTopicException {
        if (topic == null || topic.trim().equals("")) {
            throw new InvalidTopicException("topic can not be empty");
        }
    }

    @Override
    public void update(CustomerRecord<K, V> period) {
        consume(period);
    }

    @Override
    public void consume(CustomerRecord<K, V> customerRecord) {
        consumer.consume(customerRecord);
    }

    public String getTopic() {
        return topic;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    @Override
    public void close() throws IOException {
        BrokerServer.cancellation(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        // register 为空默认未注册
        if (register == null || register.trim().equals("")) {
            return false;
        }
        if (!(obj instanceof AriesCustomer)) {
            return false;
        }
        AriesCustomer that = (AriesCustomer) obj;
        if (!register.equals(that.register)) {
            return false;
        }
        return topic.equals(that.topic);
    }

    @Override
    public int hashCode() {
        int result = topic == null ? 0 : topic.hashCode();
        result = result * 31 +(register != null ? register.hashCode() : 0);
        return result;
    }
}
