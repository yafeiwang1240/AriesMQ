package com.github.yafei1240.aries.observer;

import com.github.yafei1240.aries.common.Allocation;

import java.io.Serializable;
import java.util.List;

/**
 *  主题
 * @author wangyafei
 */
public interface Subject<T> extends Serializable {

    /**
     * 在该主题下添加观察者
     * @param observer
     */
    default boolean attach(Observer observer) {
        return getObservers().add(observer);
    }

    /**
     * 移除观察者 {@link java.util.ArrayList}
     * 观察者需事先equal方法
     *
     * @param observer
     */
    default boolean detach(Observer observer) {
        return getObservers().remove(observer);
    }

    List<Observer> getObservers();

    /**
     *  通知所有观察者执行update方法
     * @param t
     */
    default void notifyAll(T t) {
        getObservers().stream().forEach(_value -> _value.update(t));
    }

    /**
     * 随机通知一个观察者
     * @param t
     * @return
     */
    default boolean notify(T t) {
        List<Observer> observers = getObservers();
        if (observers == null || observers.size() <= 0) {
            return false;
        }
        int index = Allocation.allocation(observers.size(), Allocation.AllocationType.SEQUENCE);
        observers.get(index).update(t);
        return true;
    }

}
