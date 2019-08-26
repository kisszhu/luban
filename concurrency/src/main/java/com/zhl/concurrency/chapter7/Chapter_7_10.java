package com.zhl.concurrency.chapter7;

import java.util.concurrent.*;

/**
 * 程序清单 7-10 通过Future来取消任务.
 */
public class Chapter_7_10 {
    public static final ScheduledExecutorService taskExec = new ScheduledThreadPoolExecutor(1);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // 接下来任务将被取消
        } catch (ExecutionException e) {
            // 如果在任务中抛出了异常，那么重新抛出该异常
            throw launderThrowable(e.getCause());
        } finally {
            // 如果任务已经结束，那么执行取消操作也不会带来任何影响
            task.cancel(true); // 如果任务正在运行，那么将被中断
        }
    }

    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else
            throw new IllegalStateException("Not unchecked", t);
    }
}
