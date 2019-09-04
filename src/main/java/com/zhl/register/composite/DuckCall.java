package com.zhl.register.composite;

/**
 * 鸭鸣器
 */
public class DuckCall implements Quackable {
    Observable observable;

    public DuckCall() {
        observable = new Observable(this);
    }

    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public void notifyObservers() {
        observable.notifyObservers();
    }
    public void quack() {
        System.out.println("Kwak");
        notifyObservers();
    }
}
