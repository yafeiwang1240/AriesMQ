package com.github.yafei1240.aries.observer;

import java.io.Serializable;

/**
 *  观察者
 * @author wangyafei
 */
public interface Observer<T> extends Serializable {
    void update(T period);
}
