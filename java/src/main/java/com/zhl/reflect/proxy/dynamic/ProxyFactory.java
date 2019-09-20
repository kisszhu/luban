package com.zhl.reflect.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program codeX
 * @description:动态代理
 * @author: meilong
 * @create: 2019/09/20 09:34
 */
public class ProxyFactory {

    private IUserDao userDao;

    public ProxyFactory(IUserDao userDao) {
        this.userDao = userDao;
    }

    public <T> T getInstance() {
        T instance = (T) Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理对象");
                Object value = method.invoke(userDao, args);
                return value;
            }
        });
        return instance;
    }
}
