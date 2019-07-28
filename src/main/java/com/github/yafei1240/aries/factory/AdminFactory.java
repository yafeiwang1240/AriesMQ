package com.github.yafei1240.aries.factory;

import com.github.yafei1240.aries.client.Admin;
import com.github.yafei1240.aries.client.impl.AriesAdmin;

public class AdminFactory {
    public static Admin newAdmin() {
        return new AriesAdmin();
    }
}
