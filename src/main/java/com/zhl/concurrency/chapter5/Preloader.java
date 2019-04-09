package com.zhl.concurrency.chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask.
 */
public class Preloader {
    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }
    });
    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo loadProductInfo() {
        return new ProductInfo("A");
    }

    public ProductInfo get() throws InterruptedException {
        try {
            return future.get();
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
