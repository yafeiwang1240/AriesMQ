package com.github.yafei1240.aries.client.impl;

import com.github.yafei1240.aries.client.record.CallBack;
import com.github.yafei1240.aries.client.record.ProducerRecord;
import com.github.yafei1240.aries.client.record.RecordMetaData;
import com.github.yafei1240.aries.client.Producer;
import com.github.yafei1240.aries.exception.NoSuchTopicException;
import com.github.yafei1240.aries.server.BrokerServer;

import java.io.IOException;
import java.util.concurrent.Future;

public class AriesProducer<K, V> implements Producer<K, V> {

    @Override
    public Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return BrokerServer.sendAsync(record);
    }

    @Override
    public Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return BrokerServer.sendAsyncRandom(record);
    }

    @Override
    public Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException {
        return BrokerServer.sendAsync(record, callBack);
    }

    @Override
    public Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException {
        return BrokerServer.sendAsyncRandom(record, callBack);
    }

    @Override
    public RecordMetaData send(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return BrokerServer.send(record);
    }

    @Override
    public RecordMetaData sendRandom(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return BrokerServer.sendRandom(record);
    }

    @Override
    public void close() throws IOException {
        // do nothing
    }
}
