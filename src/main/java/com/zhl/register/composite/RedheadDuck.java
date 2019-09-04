package com.zhl.register.composite;

public class RedheadDuck implements Quackable {
    public void quack() {
        System.out.println("Quack");
        notifyObservers();
    }

    Observable observable;

    public RedheadDuck() {
        observable = new Observable(this);
    }

    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public void notifyObservers() {
        observable.notifyObservers();
    }
}
