package com.zhl.reflect.proxy.cglib;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/20 12:41
 */
public class App {
    public static void main(String[] args) {
        UserDao dao = new UserDao();
        UserDao proxy = (UserDao) new ProxyFactory(dao).getInstance();
        proxy.save("张三");
    }
}
