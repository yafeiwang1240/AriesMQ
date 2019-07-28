package com.github.yafei1240.aries.server.impl;

import com.github.yafei1240.aries.client.record.CustomerRecord;
import com.github.yafei1240.aries.observer.Observer;
import com.github.yafei1240.aries.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class ProducerSubject implements Subject<CustomerRecord> {

    private List<Observer> observers;

    public ProducerSubject() {
        observers = new ArrayList<>();
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }
}
