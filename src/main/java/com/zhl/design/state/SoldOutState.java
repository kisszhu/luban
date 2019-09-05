package com.zhl.design.state;

/**
 * 售罄状态.
 */
public class SoldOutState implements State {
    GumballMachine gumballMachine;

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
