package com.zhl.design.duck;

/**
 * Created by zhuhailong-dc on 2018/5/1.
 * 具体的类充当行为
 */
public class MetuQuack implements QuackBehavior {
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
