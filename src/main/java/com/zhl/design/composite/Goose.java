package com.zhl.design.composite;

/**
 * 鹅
 */
public class Goose {
    /**
     * 假设我们想要在所有使用鸭子的地方使用鹅，毕竟鹅会叫、会飞、会游，和鸭子差不多。
     * 什么模式可以让我们轻易地将鸭子和大鹅混杂在一起呢：适配器模式
     */
    public void honk() {
        System.out.println("Honk");
    }
}
