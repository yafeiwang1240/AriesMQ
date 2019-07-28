package com.github.yafei1240.aries.client.record;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认CallBack, 仅输出日志
 */
public class DefaultCallBack implements CallBack {

    private static Logger logger = LoggerFactory.getLogger(DefaultCallBack.class);

    private static DefaultCallBack instance;

    private static Object[] lock = new Object[0];

    public static DefaultCallBack getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DefaultCallBack();
                }
            }
        }
        return instance;
    }

    @Override
    public <T> void invoke(T result, Exception exception) {
        if (exception != null) {
            logger.error(exception.getMessage(), exception);
            return;
        }
        logger.info(result == null ? "null" : result.toString());
    }
}
