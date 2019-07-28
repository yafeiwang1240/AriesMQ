package com.github.yafei1240.aries.client.impl;

import com.github.yafei1240.aries.model.CreateTopicsResult;
import com.github.yafei1240.aries.model.DeleteTopicsResult;
import com.github.yafei1240.aries.client.record.NewTopic;
import com.github.yafei1240.aries.client.Admin;

import java.util.Collection;

public class AriesAdmin implements Admin {

    @Override
    public CreateTopicsResult createTopics(Collection<NewTopic> topics) {
        return null;
    }

    @Override
    public DeleteTopicsResult deleteTopics(Collection<NewTopic> topics) {
        return null;
    }
}
