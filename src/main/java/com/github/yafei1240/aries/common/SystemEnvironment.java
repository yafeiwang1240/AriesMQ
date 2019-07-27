package com.github.yafei1240.aries.common;

public class SystemEnvironment {

    private static final int CPU_NUM;

    static {
        CPU_NUM = Runtime.getRuntime().availableProcessors();
    }

    public static int getCpuNum() {
        return CPU_NUM;
    }
}
