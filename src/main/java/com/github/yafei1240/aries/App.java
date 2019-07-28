package com.github.yafei1240.aries;

import com.github.yafei1240.aries.common.SystemEnvironment;
import com.github.yafei1240.aries.observer.Observer;
import com.github.yafei1240.aries.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello World
 */
public class App {
    public static void main(String[] args) {
//        System.out.println(SystemEnvironment.getCpuNum());
        test();
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
