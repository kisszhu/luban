package com.zhl.design.chocolate;

/**
 * Created by zhuhailong-dc on 2018/5/23.
 * 单例
 */
public class Singleton {

    private volatile static Singleton uniqueInstance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            // 这段同步代码只执行一次
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

}
