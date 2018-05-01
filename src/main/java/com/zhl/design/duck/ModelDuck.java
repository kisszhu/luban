package com.zhl.design.duck;

/**
 * Created by zhuhailong-dc on 2018/5/1.
 * 模型鸭子
 */
public class ModelDuck extends Duck{

    public ModelDuck(){
        flyBehaviro=new FlyNoWay();
        quackBehavior=new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }
}
