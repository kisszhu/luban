package com.zhl.concurrency.chapter7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 不可靠的取消操作把生产者置于阻塞的操作中（不要这么做）.
 * PrimeGenerator中的取消机制最终会使得搜索素数的任务退出，但在退出过程中需要一定的时间。
 * 然而，如果使用这个方法的任务调用了一个阻塞方法，例如BlockingQueue.put，那么可能会
 * 产生一个更严重的问题——任务可能永远不会检查取消标志，因此永远不会结束。
 * <p>
 * BrokenPrimeProducer就说明了这个问题。生产者线程生成素数，并将它们放入一个阻塞队列。如果
 * 生产者的速度超过消费者的处理速度，队列将被填满，put方法也会阻塞。当生产者在put方法中阻塞
 * 时，如果消费者希望取消生产的任务，那么将发送什么情况？它可以调用cancel方法来设置cancelled
 * 标志，但此时生产者却永远不会检查这个标志，因为它无法从阻塞的put方法中恢复过来
 */
public class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled)
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException consumed) {

        }
    }

    public void cancel() {
        cancelled = true;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> primes = new LinkedBlockingDeque<>(10);
        BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
        producer.start();
        try {
            Thread.sleep(1000);
            System.out.println("The First " + primes.take());
        } finally {
            // 会发现执行完后，线程不退出，程序阻塞
            producer.cancel();
        }
    }
}
