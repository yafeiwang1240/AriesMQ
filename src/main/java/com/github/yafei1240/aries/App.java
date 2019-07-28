package com.github.yafei1240.aries;

import com.github.yafei1240.aries.client.Admin;
import com.github.yafei1240.aries.client.Customer;
import com.github.yafei1240.aries.client.Producer;
import com.github.yafei1240.aries.client.record.NewTopic;
import com.github.yafei1240.aries.client.record.ProducerRecord;
import com.github.yafei1240.aries.client.record.RecordMetaData;
import com.github.yafei1240.aries.exception.InvalidTopicException;
import com.github.yafei1240.aries.exception.NoSuchTopicException;
import com.github.yafei1240.aries.factory.AdminFactory;
import com.github.yafei1240.aries.factory.CustomerFactory;
import com.github.yafei1240.aries.factory.ProducerFactory;
import com.github.yafei1240.aries.observer.Observer;
import com.github.yafei1240.aries.observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Hello World
 */
public class App {
    public static void main(String[] args) {
//        System.out.println(SystemEnvironment.getCpuNum());
//        test();
        try {
            test1();
        } catch (NoSuchTopicException e) {
            e.printStackTrace();
        } catch (InvalidTopicException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void test1() throws NoSuchTopicException, InvalidTopicException, ExecutionException, InterruptedException {
        Admin admin = AdminFactory.newAdmin();
        List<NewTopic> newTopics = new ArrayList<>();
        newTopics.add(new NewTopic("k_hello_world"));
        admin.createTopics(newTopics);
        Producer<String, String> producer = ProducerFactory.newProducer();
        Customer<String, String> customer = CustomerFactory.newCustomer("k_hello_world",
                _value -> System.out.println(_value.value()));
        Customer<String, String> customer1 = CustomerFactory.newCustomer("k_hello_world",
                _value -> System.out.println(_value.value()));
        Future<RecordMetaData> future = producer.sendAsyncRandom(new ProducerRecord<>("k_hello_world", "hello", "world", System.currentTimeMillis()));
        RecordMetaData data = future.get();
        System.out.println(data.topic());
    }

    public static void test() {
        System.out.println("Hello world");
        SubjectObject subjectObject = new SubjectObject();
        subjectObject.attach(new ObserverWorld());
        subjectObject.attach(new ObserverHello());
        for (int i = 0; i < 10; i++) {
            subjectObject.notifyObserver(i);
            if (i % 3 == 0 && i > 0) {
                subjectObject.notifyAllObserver(i);
            }
        }
    }

    public static class SubjectObject implements Subject {

        protected List<Observer> observers = new ArrayList<>();

        @Override
        public List<Observer> getObservers() {
            return observers;
        }
    }

    public static class ObserverHello implements Observer {

        @Override
        public void update(Object period) {
            System.out.println("ObserverHello: " + period);
        }
    }

    public static class ObserverWorld implements Observer {

        @Override
        public void update(Object period) {
            System.out.println("ObserverWorld: " + period);
        }
    }
}
