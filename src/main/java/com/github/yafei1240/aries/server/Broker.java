package com.github.yafei1240.aries.server;

import com.github.yafei1240.aries.exception.NoSuchTopicException;
import com.github.yafei1240.aries.model.CreateTopicsResult;
import com.github.yafei1240.aries.model.DeleteTopicsResult;
import com.github.yafei1240.aries.client.record.CallBack;
import com.github.yafei1240.aries.client.record.NewTopic;
import com.github.yafei1240.aries.client.record.ProducerRecord;
import com.github.yafei1240.aries.client.record.RecordMetaData;
import com.github.yafei1240.aries.client.impl.AriesCustomer;

import java.util.Collection;
import java.util.concurrent.Future;

public interface Broker {
    <K, V> Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record) throws NoSuchTopicException;
    <K, V> Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record) throws NoSuchTopicException;
    <K, V> Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException;
    <K, V> Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException;
    <K, V> RecordMetaData send(ProducerRecord<K, V> record) throws NoSuchTopicException;
    <K, V> RecordMetaData sendRandom(ProducerRecord<K, V> record) throws NoSuchTopicException;
    CreateTopicsResult createTopics(Collection<NewTopic> topics);
    DeleteTopicsResult deleteTopics(Collection<NewTopic> topics);
    <K, V> boolean register(AriesCustomer<K, V> customer) throws NoSuchTopicException;
    <K, V> boolean cancellation (AriesCustomer<K, V> customer);
}
