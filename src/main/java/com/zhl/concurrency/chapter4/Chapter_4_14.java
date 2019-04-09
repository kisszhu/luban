package com.zhl.concurrency.chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 客户端加锁机制.
 */
public class Chapter_4_14<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent)
            list.add(x);
        return absent;
    }
}
