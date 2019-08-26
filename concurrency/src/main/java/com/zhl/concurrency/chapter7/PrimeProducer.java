package com.zhl.concurrency.chapter7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * 通过中断来取消.
 */
public class PrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException consumes) {
            /* 允许线程退出 */
        }
    }

    public void cancel() {
        interrupt();
    }
}
