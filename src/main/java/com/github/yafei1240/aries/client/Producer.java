package com.github.yafei1240.aries.client;

import com.github.yafei1240.aries.client.record.CallBack;
import com.github.yafei1240.aries.client.record.ProducerRecord;
import com.github.yafei1240.aries.client.record.RecordMetaData;
import com.github.yafei1240.aries.exception.NoSuchTopicException;

import java.io.Closeable;
import java.util.concurrent.Future;

public interface Producer<K, V> extends Closeable {
    /**
     * 异步消息发布
     * @param record
     * @return
     * @throws NoSuchTopicException
     */
    Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record) throws NoSuchTopicException;

    /**
     * 异步随机发送给一个消费者
     * @param record
     * @return
     * @throws NoSuchTopicException
     */
    Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record) throws NoSuchTopicException;

    /**
     * 带回调的异步消息发布
     * @param record
     * @param callBack
     * @return
     * @throws NoSuchTopicException
     */
    Future<RecordMetaData> sendAsync(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException;

    /**
     * 带回调的异步消息随机发布
     * @param record
     * @param callBack
     * @return
     * @throws NoSuchTopicException
     */
    Future<RecordMetaData> sendAsyncRandom(ProducerRecord<K, V> record, CallBack callBack) throws NoSuchTopicException;

    /**
     * 同步发布消息
     * @param record
     * @return
     * @throws NoSuchTopicException
     */
    RecordMetaData send(ProducerRecord<K, V> record) throws NoSuchTopicException;

    /**
     * 同步随机发布给一个消费者消息
     * @param record
     * @return
     * @throws NoSuchTopicException
     */
    RecordMetaData sendRandom(ProducerRecord<K, V> record) throws NoSuchTopicException;
}
