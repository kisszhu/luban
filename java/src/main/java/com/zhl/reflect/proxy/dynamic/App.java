package com.zhl.reflect.proxy.dynamic;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/20 09:48
 */
public class App {

    public static void main(String[] args) {
        IUserDao userDao = new UserDao();

        IUserDao proxy = new ProxyFactory(userDao).getInstance();
        proxy.save("张三");
    }
}
