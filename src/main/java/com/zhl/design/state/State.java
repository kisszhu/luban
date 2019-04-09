package com.zhl.design.state;

/**
 * 状态接口.
 */
public interface State {
    void insertQuarter();

    void ejectQuarter();

    void tumCrank();

    void dispense();
}
