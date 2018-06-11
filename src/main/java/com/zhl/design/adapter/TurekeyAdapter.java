package com.zhl.design.adapter;

public class TurekeyAdapter implements Duck {

    Turkey turkey;

    public TurekeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    public void quack() {
        turkey.gobble();
    }

    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
