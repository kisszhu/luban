package com.zhl.concurrency.chapter5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界.
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAddes = false;
        try {
            wasAddes = set.add(o);
            System.out.println("Add " + o + " SUCCESS");
            return wasAddes;
        } finally {
            if (!wasAddes)
                sem.release();
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(0);
        if (wasRemoved)
            sem.release();
        return wasRemoved;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet bound = new BoundedHashSet<String>(3);
        bound.add("A");
        bound.add("B");
        bound.add("C");
        bound.add("D");// The D is not permitted to set
    }
}
