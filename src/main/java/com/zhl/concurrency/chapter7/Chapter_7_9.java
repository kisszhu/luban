package com.zhl.concurrency.chapter7;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 程序清单 7-9 在专门的线程中中断任务.
 */
public class Chapter_7_9 {

    private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(1);

    public static void timedRun(final Runnable r,
                                long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (t != null) {
                    throw launderThrowable(t);
                }
            }

            RuntimeException launderThrowable(Throwable t) {
                if (t instanceof RuntimeException) {
                    return (RuntimeException) t;
                } else if (t instanceof Error) {
                    throw (Error) t;
                } else
                    throw new IllegalStateException("Not unchecked", t);
            }
        }

        RethrowableTask task = new RethrowableTask();
        Thread taskThread = new Thread(task);
        taskThread.start();

        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);

        /**
         * 在启动任务线程之后，timedRun将执行一个限时的join方法。在join返回后，它将检查任务中是否有
         * 异常抛出，如果有的话，则会在调用timedRun的线程中再次抛出该异常。
         * 由于Throwable将在两个线程之间共享，因此该变量被声明为volatile类型，从而确保安全地将其从
         * 任务线程发布到timedRun线程
         */
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };
        timedRun(r, 10, TimeUnit.SECONDS);
    }
}
