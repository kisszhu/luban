package com.zhl.register.proxy;

/**
 * 售罄状态.
 */
public class SoldOutState implements State {
    transient GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {

    }

    public void ejectQuarter() {

    }

    public void tumCrank() {

    }

    public void dispense() {

    }
}
