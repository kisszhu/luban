package com.zhl.concurrency.chapter5;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 隐藏迭代器.
 */
public class HiddenIterator {
    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            add(r.nextInt());
        }
        // 隐藏迭代器
        System.out.println("DEBUG: added then elements to " + set);
    }
}
