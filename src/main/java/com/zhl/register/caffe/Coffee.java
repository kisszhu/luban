package com.zhl.register.caffe;

/**
 * Created by zhuhailong-dc on 2018/5/24.
 * 咖啡
 */
public class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}
