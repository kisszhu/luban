package com.zhl.design.composite;

public class RubberDuck implements Quackable {
    public void quack() {
        System.out.println("Squeak");
        notifyObservers();
    }

    Observable observable;

    public RubberDuck() {
        observable = new Observable(this);
    }

    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public void notifyObservers() {
        observable.notifyObservers();
    }
}
