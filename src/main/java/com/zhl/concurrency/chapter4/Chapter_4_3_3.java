package com.zhl.concurrency.chapter4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 委托失效.
 */
public class Chapter_4_3_3 {

    // 不变性条件
    private final AtomicInteger lower = new AtomicInteger();
    private final AtomicInteger upper = new AtomicInteger();

    /**
     *
     */
    public void setLower(int i) {
        // 注意-不安全的"先检查后执行"
        if (i > upper.get()) {
            throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        // 注意-不安全的"先检查后执行"
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }
}
