package com.zhl.concurrency.chapter7;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * 为LogWriter提供可靠关闭操作的方法是解决竞态条件问题，因而要使日志消息的提交操作成为原则。然而，我们
 * 不希望在消息加入队列时去持有一个锁，因为put方法本身就可以阻塞。我们采用的方法是：通过原子方式来检查关闭
 * 请求，并且有条件地递增一个计数器来"保存"提交消息的权力.
 */
public class LogService {

    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    private boolean isShutDown;
    private int reservations;

    public LogService(BlockingQueue queue, PrintWriter writer) {
        this.queue = queue;
        this.loggerThread = new LoggerThread();
        this.writer = writer;
    }

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutDown = true;
        }
        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutDown)
                throw new IllegalStateException("");
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.this) {
                            if (isShutDown && reservations == 0) {
                                break;
                            }
                        }
                        String msg = queue.take();
                        synchronized (LogService.this) {
                            --reservations;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {
                        /* retry */
                    }
                }
            } finally {
                writer.close();
            }
        }
    }
}
