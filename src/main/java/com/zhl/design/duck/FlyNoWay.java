package com.zhl.design.duck;

/**
 * Created by zhuhailong-dc on 2018/5/1.
 * 具体的类充当行为
 */
public class FlyNoWay implements FlyBehaviro {
    public void fly() {
        System.out.println("I can't fly");
    }
}
