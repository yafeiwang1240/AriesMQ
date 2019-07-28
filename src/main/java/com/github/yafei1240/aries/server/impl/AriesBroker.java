package com.github.yafei1240.aries.server.impl;

import com.github.yafei1240.aries.client.impl.AriesCustomer;
import com.github.yafei1240.aries.exception.NoSuchTopicException;
import com.github.yafei1240.aries.model.CreateTopicsResult;
import com.github.yafei1240.aries.model.DeleteTopicsResult;
import com.github.yafei1240.aries.client.record.*;
import com.github.yafei1240.aries.server.Broker;
import com.github.yafei1240.aries.server.Manager;

import java.util.Collection;
import java.util.concurrent.Future;

public class AriesBroker implements Broker {

    private Manager manager;

    private CallBack callBack;

    public AriesBroker() {
        manager = new AriesManager();
        callBack = DefaultCallBack.getInstance();
    }

    @Override
    public <K, V> Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return manager.sendAsync(record, callBack, true);
    }

    @Override
    public <K, V> Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return manager.sendAsync(record, callBack, false);
    }

    @Override
    public <K, V> Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException {
        return manager.sendAsync(record, callBack, true);
    }

    @Override
    public <K, V> Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException {
        return manager.sendAsync(record, callBack, false);
    }

    @Override
    public <K, V> RecordMetaData send(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return manager.send(record, true);
    }

    @Override
    public <K, V> RecordMetaData sendRandom(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return manager.send(record, false);
    }

    @Override
    public CreateTopicsResult createTopics(Collection<NewTopic> topics) {
        return manager.createTopics(topics);
    }

    @Override
    public DeleteTopicsResult deleteTopics(Collection<NewTopic> topics) {
        return manager.deleteTopics(topics);
    }

    @Override
    public <K, V> boolean register(AriesCustomer<K, V> customer) throws NoSuchTopicException {
        return manager.register(customer);
    }

    @Override
    public <K, V> boolean cancellation(AriesCustomer<K, V> customer) {
        return manager.cancellation(customer);
    }
}
