package com.zhl.design.duck;

/**
 * Created by zhuhailong-dc on 2018/5/1.
 * Duck固定属性、固定行为的抽象类
 */
public abstract class Duck {

    FlyBehaviro flyBehaviro;
    QuackBehavior quackBehavior;

    public abstract void display();

    public void performFly(){
        flyBehaviro.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void swim(){
        System.out.println("All ducks float,even decoys");
    }

    public void setFlyBehaviro(FlyBehaviro fb){
        flyBehaviro=fb;
    }
    public void setQuackBehavior(QuackBehavior qb){
        quackBehavior=qb;
    }

}
