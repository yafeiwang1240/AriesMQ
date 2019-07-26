package com.github.yafei1240.aries.common;

import java.util.Random;

/**
 * 随机分配算法
 */
public class Allocation {

    public enum AllocationType {
        RANDOM,
        TIME,
        SEQUENCE,
    }

    private static Random random;

    private static volatile int sequence = 0;

    private static final int INTEGER_CACHE_HIGH = 127;

    static {
        random = new Random();
    }

    public static int allocation(int size, AllocationType type) {
        switch (type) {
            case TIME:
                return time(size);
            case RANDOM:
                return random(size);
            case SEQUENCE:
                return sequence(size);
            default:
                return 0;
        }
    }

    /**
     * 随机派发策略
     * @param size
     * @return
     */
    private static int random(int size) {
        if(size <= 0) {
            return 0;
        }
        int index = random.nextInt(size);
        return index % size;
    }

    /**
     * 时间轴策略
     * @param size
     * @return
     */
    private static int time(int size) {
        if(size <= 0) {
            return 0;
        }
        long base = System.currentTimeMillis();
        return (int) base % size;
    }

    /**
     * 顺序分发
     * @param size
     * @return
     */
    private static int sequence(int size) {
        if(size <= 0) {
            return 0;
        }
        int base = getSequence();
        if(base == 0) {
            return base;
        }
        return size > base ? base : base % size;
    }

    /**
     * 直接使用Integer缓存中的数据
     * @return
     */
    private static int getSequence() {
        if(sequence >= INTEGER_CACHE_HIGH) {
            sequence = 0;
        }else {
            sequence++;
        }
        return sequence;
    }
}
