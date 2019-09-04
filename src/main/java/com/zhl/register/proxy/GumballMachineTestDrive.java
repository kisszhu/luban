package com.zhl.register.proxy;

import java.rmi.Naming;

/**
 * 测试类.
 */
public class GumballMachineTestDrive {
    public static void main(String[] args) {
        GumballMachineRemote gumballMachine = null;
        try {
            gumballMachine = new GumballMachine("", 5);
            Naming.rebind("//localhost/remote", gumballMachine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
