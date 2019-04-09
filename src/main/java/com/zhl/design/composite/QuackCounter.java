package com.zhl.design.composite;

/**
 * 装饰者模式
 * 呱呱叫学家为所有拥有可呱呱叫行为的事物着迷。其中一件他们经常研究的事是：
 * 在一群鸭子中，会有多少呱呱叫声？
 * 我们要如何在不变化鸭子类的情况下，计算呱呱叫的次数呢？
 */
public class QuackCounter implements Quackable {
    Quackable duck;
    static int numberOfQuacks;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
        observable = new Observable(this);
    }

    public void quack() {
        duck.quack();
        numberOfQuacks++;
        notifyObservers();
    }

    public static int getQuacks() {
        return numberOfQuacks;
    }

    Observable observable;

    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public void notifyObservers() {
        observable.notifyObservers();
    }
}
