package com.github.yafei1240.aries.factory;

import com.github.yafei1240.aries.client.record.CustomerRecord;
import com.github.yafei1240.aries.model.ManagerPack;
import com.github.yafei1240.aries.observer.Subject;

public class ManagerPackFactory {
    public static ManagerPack newManagerPack(Subject<CustomerRecord> subject) {
        return new ManagerPack(subject);
    }
}
