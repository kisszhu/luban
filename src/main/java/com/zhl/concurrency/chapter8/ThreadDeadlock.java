package com.zhl.concurrency.chapter8;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 在单线程Executor中任务发生死锁.
 * RenderPageTask向Executor提交了两个任务来获取网页的页眉和页脚，绘制页眉，等待获取页眉和页脚任务的
 * 结果，然后将页眉、页面主题和页脚组合起来并形成最终的页眉。
 *
 * 在单线程Executor中任务发生死锁
 */
public class ThreadDeadlock {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    /**
     * 对应一个单线程话的Executor,一个任务将另一个任务提交到相同的Executor中，并等待新提交的任务的结果，
     * 这总会引发死锁。第二个任务滞留在工作队列中，直到第一个任务完成，但是第一个任务不会完成，
     * 因为它在等待第二个任务的完成。
     */
    public class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<?> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // 将发生死锁——由于任务在等待子任务的结果
            return header.get() + page + footer.get();
        }
    }

    public String renderBody() {
        return null;
    }
}

class LoadFileTask implements Runnable {
    private final String file;

    public LoadFileTask(String file) {
        this.file = file;
    }

    @Override
    public void run() {

    }
}
