package com.github.yafei1240.aries.server;

import com.github.yafei1240.aries.client.impl.AriesCustomer;
import com.github.yafei1240.aries.client.record.CallBack;
import com.github.yafei1240.aries.client.record.NewTopic;
import com.github.yafei1240.aries.client.record.ProducerRecord;
import com.github.yafei1240.aries.client.record.RecordMetaData;
import com.github.yafei1240.aries.exception.NoSuchTopicException;
import com.github.yafei1240.aries.model.CreateTopicsResult;
import com.github.yafei1240.aries.model.DeleteTopicsResult;
import com.github.yafei1240.aries.server.impl.AriesBroker;

import java.util.Collection;
import java.util.concurrent.Future;

public class BrokerServer {

    private static final Broker broker;
    static {
        broker = new AriesBroker();
    }

    public static <K, V> Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return broker.sendAsync(record);
    }

    public static <K, V> Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return broker.sendAsyncRandom(record);
    }

    public static <K, V> Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException {
        return broker.sendAsync(record, callBack);
    }

    public static <K, V> Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException {
        return broker.sendAsyncRandom(record, callBack);
    }

    public static <K, V> RecordMetaData send(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return broker.send(record);
    }

    public static <K, V> RecordMetaData sendRandom(ProducerRecord<K, V> record) throws NoSuchTopicException {
        return broker.sendRandom(record);
    }

    public static CreateTopicsResult createTopics(Collection<NewTopic> topics) {
        return broker.createTopics(topics);
    }

    public static DeleteTopicsResult deleteTopics(Collection<NewTopic> topics) {
        return broker.deleteTopics(topics);
    }

    public static <K, V> boolean register(AriesCustomer<K, V> customer) throws NoSuchTopicException {
        return broker.register(customer);
    }

    public static <K, V> boolean cancellation(AriesCustomer<K, V> customer) {
        return broker.cancellation(customer);
    }
}
