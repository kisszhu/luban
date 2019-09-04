package com.zhl.register.proxy;

import java.io.Serializable;

/**
 * 状态接口.
 */
public interface State extends Serializable {
    void insertQuarter();

    void ejectQuarter();

    void tumCrank();

    void dispense();
}
