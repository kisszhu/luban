package com.zhl.concurrency.chapter5;

import java.util.concurrent.CountDownLatch;

/**
 * 在计时测试中使用CountDownLatch来启动和停止线程.
 */
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            /**
             * Thread表示线程
             */
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {
                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        long res = new TestHarness().timeTasks(100, new TestRunnable());
        System.out.println("The time is " + res);
    }
}

/**
 * Runnable表示任务
 */
class TestRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("1");
    }
}

