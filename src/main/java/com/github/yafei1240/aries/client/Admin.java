package com.github.yafei1240.aries.client;

import com.github.yafei1240.aries.model.CreateTopicsResult;
import com.github.yafei1240.aries.model.DeleteTopicsResult;
import com.github.yafei1240.aries.client.record.NewTopic;

import java.util.Collection;

/**
 * AriesMQ 管理员用户
 */
public interface Admin {

    /**
     * 创建topic
     * @param topics
     * @return
     */
    CreateTopicsResult createTopics(Collection<NewTopic> topics);

    /**
     * 删除topic
     * @param topics
     * @return
     */
    DeleteTopicsResult deleteTopics(Collection<NewTopic> topics);
}
