package com.zhl.concurrency.chapter4;

/**
 * Java监视器模式.
 */
public class PrivateLock {
    private final Object myLock = new Object();
    Widget widget;

    void someMethod(){
        synchronized (myLock){
            // 访问或修改Widget的状态
        }
    }
}

class Widget {

}
