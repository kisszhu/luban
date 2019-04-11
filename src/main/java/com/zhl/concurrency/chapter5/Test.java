package com.zhl.concurrency.chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {
    private final FutureTask<Product> future = new FutureTask<Product>(new Callable<Product>() {
        @Override
        public Product call() throws Exception {
            return load();
        }
    });

    public Product load() {
        return new Product();
    }

    Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public Product get() throws InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof RuntimeException) {

            } else if (cause instanceof Error) {

            } else {

            }
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        Test t = new Test();
        t.start();
        Product product = t.get();
        System.out.println(product.a);
    }

}

class Product {
    String a = "1";

    public Product() {
        a = "2";
    }
}
