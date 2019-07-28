package com.github.yafei1240.aries.server.impl;

import com.github.yafei1240.aries.client.impl.AriesCustomer;
import com.github.yafei1240.aries.client.record.*;
import com.github.yafei1240.aries.exception.NoSuchTopicException;
import com.github.yafei1240.aries.factory.AriesScanFactory;
import com.github.yafei1240.aries.factory.ManagerPackFactory;
import com.github.yafei1240.aries.formation.Scanning;
import com.github.yafei1240.aries.model.CreateTopicsResult;
import com.github.yafei1240.aries.model.DeleteTopicsResult;
import com.github.yafei1240.aries.model.ManagerPack;
import com.github.yafei1240.aries.model.TopicResult;
import com.github.yafei1240.aries.observer.Subject;
import com.github.yafei1240.aries.server.Manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

public class AriesManager implements Manager {

    private Map<String, ManagerPack> managerCache;

    private Scanning scanning;

    private Object[] lock;

    public AriesManager() {
        managerCache = new HashMap<>();
        lock = new Object[0];
        scanning = AriesScanFactory.newAriesScan();
    }

    @Override
    public <K, V> Future<RecordMetaData> sendAsync(ProducerRecord<K, V> producerRecord, CallBack callBack, boolean process) throws NoSuchTopicException {
        RecordMetaData data = new RecordMetaData(producerRecord.topic(), producerRecord.timestamp(), 1L);
        if (!managerCache.containsKey(producerRecord.topic())) {
            throw new NoSuchTopicException("no such topic name with " + producerRecord.topic());
        }
        ManagerPack pack = managerCache.get(producerRecord.topic());
        pack.setCallBack(callBack);
        pack.setProcess(process);
        return scanning.invoke(pack, producerRecord, data);
    }

    @Override
    public <K, V> RecordMetaData send(ProducerRecord<K, V> producerRecord, boolean process) throws NoSuchTopicException {
        RecordMetaData data = new RecordMetaData(producerRecord.topic(), producerRecord.timestamp(), 1L);
        if (!managerCache.containsKey(producerRecord.topic())) {
            throw new NoSuchTopicException("no such topic name with " + producerRecord.topic());
        }
        CustomerRecord customerRecord = new CustomerRecord(producerRecord.topic(), producerRecord.key(), producerRecord.value(), producerRecord.timestamp());
        Subject<CustomerRecord> subject = managerCache.get(producerRecord.topic()).getSubject();
        if (!process) {
            subject.notifyObserver(customerRecord);
        } else {
            subject.notifyAllObserver(customerRecord);
        }
        return data;
    }

    @Override
    public CreateTopicsResult createTopics(Collection<NewTopic> topics) {
        if (topics == null || topics.size() <= 0) {
            return null;
        }
        Map<String, TopicResult> resultMap = new HashMap<>();
        CreateTopicsResult result = new CreateTopicsResult(resultMap);
        synchronized (lock) {
            for (NewTopic topic : topics) {
                TopicResult topicResult;
                if (managerCache.containsKey(topic.getName())) {
                    topicResult = new TopicResult(TopicResult.Status.FAILED, topic.getName() + " already exists.");
                } else {
                    topicResult = new TopicResult(TopicResult.Status.SUCCESS, topic.getName());
                    managerCache.put(topic.getName(), ManagerPackFactory.newManagerPack(new ProducerSubject()));
                }
                resultMap.put(topic.getName(), topicResult);
            }
        }
        return result;
    }

    @Override
    public DeleteTopicsResult deleteTopics(Collection<NewTopic> topics) {
        if (topics == null || topics.size() <= 0) {
            return null;
        }
        Map<String, TopicResult> resultMap = new HashMap<>();
        DeleteTopicsResult result = new DeleteTopicsResult(resultMap);
        synchronized (lock) {
            for (NewTopic topic : topics) {
                TopicResult topicResult;
                if (!managerCache.containsKey(topic.getName())) {
                    topicResult = new TopicResult(TopicResult.Status.FAILED, topic.getName() + " no exists.");
                } else {
                    managerCache.remove(topic.getName());
                    topicResult = new TopicResult(TopicResult.Status.SUCCESS, topic.getName());
                }
                resultMap.put(topic.getName(), topicResult);
            }
        }
        return result;
    }

    @Override
    public <K, V> boolean register(AriesCustomer<K, V> customer) throws NoSuchTopicException {
        if (!managerCache.containsKey(customer.getTopic())) {
            throw new NoSuchTopicException("no such topic");
        }
        customer.setRegister(UUID.randomUUID().toString());
        return managerCache.get(customer.getTopic()).getSubject().attach(customer);
    }

    @Override
    public <K, V> boolean cancellation(AriesCustomer<K, V> customer) {
        if (!managerCache.containsKey(customer.getTopic())) {
            return false;
        }
        return managerCache.get(customer.getTopic()).getSubject().detach(customer);
    }
}
