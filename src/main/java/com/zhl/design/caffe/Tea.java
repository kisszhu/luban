package com.zhl.design.caffe;

/**
 * Created by zhuhailong-dc on 2018/5/24.
 * 茶
 */
public class Tea extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Lemon'");
    }
}
