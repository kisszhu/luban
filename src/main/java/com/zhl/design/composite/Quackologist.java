package com.zhl.design.composite;

public class Quackologist implements Observer {
    public void update(QuackObservable duck) {
        System.out.println("Quackologist: " + duck + "just quacked.");
    }
}
