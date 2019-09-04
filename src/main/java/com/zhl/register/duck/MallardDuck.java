package com.zhl.register.duck;

/**
 * Created by zhuhailong-dc on 2018/5/1.
 * -
 */
public class MallardDuck extends Duck {
    public MallardDuck(){
        //TODO 后期要修改掉，这块是面向实现编程了，应该是面向接口编程
        quackBehavior=new Quack();
        flyBehaviro=new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
