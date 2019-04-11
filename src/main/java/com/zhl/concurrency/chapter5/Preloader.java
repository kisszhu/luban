package com.zhl.concurrency.chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask.
 */
public class Preloader {
    private final FutureTask<ProductInfo> future =
            new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
                @Override
                public ProductInfo call() throws Exception {
                    return loadProductInfo();
                }
            });
    private final Thread thread = new Thread(future);

    // 由于在构造函数或静态初始化方法中启动线程并不是一个好方法，因此提供了一个start方法来启动
    public void start() {
        thread.start();
    }

    public ProductInfo loadProductInfo() {
        return new ProductInfo("A");
    }

    public ProductInfo get() throws InterruptedException {
        try {
            return future.get();
            /**
             * Callable表示的任务可以抛出受检查的或未受检查的异常，并且任何代码都可能抛出一个
             * Error。无论任务代码抛出什么异常，都会被封装到一个ExecutionException中，并在
             * Future.get中被重新抛出。
             *
             * 在Preloader中，当get方法抛出ExecutionException时，可能是以下三种情况之一：
             * Callable抛出的受检查异常，RuntimeException，以及Error
             */
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
//            if (cause instanceof DataLoadException) {
//                throw (DataLoadException) cause;
//            } else {
            throw launderThrowable(cause);
//            }
        }
    }

    /**
     * 如果Throwable是Error，那么抛出它，如果是RuntimeException，那么返回它，否则抛出
     * IllegalStateException
     */
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else
            throw new IllegalStateException("Not unchecked", t);
    }

    public static void main(String[] args) throws InterruptedException {
        Preloader loader = new Preloader();
        loader.start();
        ProductInfo info = loader.get();
        System.out.println("The value is " + info.a);
    }
}

class ProductInfo {
    public final String a;

    public ProductInfo(String a) {
        this.a = a;
    }
}
