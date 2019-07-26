package com.github.yafei1240.aries.observer;

/**
 *  观察者
 * @author wangyafei
 */
public interface Observer<T> {
    void update(T period);
}
