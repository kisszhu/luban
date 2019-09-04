package com.zhl.register.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 远程接口.
 * 要把我们的代码改成使用代理，第一个步骤是让GumballMachine变成可以接受远程调用。
 * 换句话说，我们要把它变成一个服务。做法如下：
 * 1、为GumballMachine创建一个远程接口。该口提供了一组可以远程调用的方法
 * 2、确定接口的所有返回类型都是可序列化的
 * 3、在一个具体类中，实现此接口
 */
public interface GumballMachineRemote extends Remote {
    int getCount() throws RemoteException;
    String getLocation() throws RemoteException;
    // 所有的返回类型都必须是原语类型或可序列化类型
    State getState() throws RemoteException;
}
