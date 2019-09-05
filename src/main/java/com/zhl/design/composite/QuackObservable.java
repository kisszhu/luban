package com.zhl.design.composite;


/**
 * 观察者模式
 * 组合工作得很顺畅，现在我们有另一个相反的要求：我们也要追踪个别的鸭子。
 * Observable就是被观察的对象。Observable需要注册和通知观察者的方法
 */
public interface QuackObservable {
    void registerObserver(Observer observer);

    void notifyObservers();
}
