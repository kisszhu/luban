package com.zhl.reflect.proxy.static1;

/**
 * @program codeX
 * @description:介绍静态代理
 * @author: meilong
 * @create: 2019/09/20 09:19
 */
public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        ProxyUserDao proxy = new ProxyUserDao(userDao);
        proxy.save("张三");
    }
}
