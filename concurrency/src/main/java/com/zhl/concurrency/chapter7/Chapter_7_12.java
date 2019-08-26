package com.zhl.concurrency.chapter7;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 程序清单 7-12 通过newTaskFor将非标准的取消操作封装在一个任务中.
 */
public class Chapter_7_12 {
    /**
     * 我们可以通过newTaskFor方法进一步优化ReaderThread中封装非标准取消的技术，这是Java 6在
     * ThreadPoolExecute中的新增功能。当把一个Callable提交给ExecutorService时，submit方法会
     * 返回一个Future，我们可以通过这个Future来取消任务。
     *
     * newTaskFor是一个工厂方法，它将创建Future来代表任务。newTaskFor还能返回一个RunnableFuture接口，
     * 该接口扩展了Future和Runnable（并由FutureTask实现）。
     *
     * 通过定制表示任务的Future可以改变Future.cancel的行为。例如，定制的取消代码可以实现日志记录
     * 或者收集取消操作的统计信息，以及取消一些不响应中断的操作。通过改写interrupt方法，ReaderThread
     * 可以取消基于套接字的线程。同样，通过改写任务的Future.cancel方法也可以实现类似的功能。
     */
}

/**
 * CancellableTask中定义了一个CancellableTask接口，该接口扩展了Callable，
 * 并增加了一个cancel方法和一个newTask工厂方法来构造RunnableFuture。
 */
interface CancellableTask<T> extends Callable<T> {
    void cancel();

    RunnableFuture<T> newTask();
}

/**
 * CancellingExecutor扩展了ThreadPoolExecutor，并通过改写newTaskFor使得
 * CancellableTask可以创建自己的Future。
 */
class CancellingExecutor extends ThreadPoolExecutor {

    public CancellingExecutor() {
        super(0, 0, 0, null, null);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancellableTask) {
            return ((CancellableTask<T>) callable).newTask();
        } else {
            return super.newTaskFor(callable);
        }
    }
}

/**
 * SocketUsingTask实现了CancellableTask，并定义了Future.cancel来关闭套接字和调用
 * super.cancel。如果SocketUsingTask通过其自己的Future来取消，那么底层的套接字
 * 将被关闭并且线程将被中断。因为它提高了任务对取消操作的响应性：不仅能够在调用可中断
 * 方法的同时响应取消操作，而且还能调用可阻调的套接字I/O方法。
 */
abstract class SocketUsingTask<T> implements CancellableTask<T> {
    private Socket socket;

    protected synchronized void setSocket(Socket s) {
        socket = s;
    }

    public synchronized void cancel() {
        try {
            if (socket != null)
                socket.close();
        } catch (IOException ignored) {

        }
    }

    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsingTask.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}