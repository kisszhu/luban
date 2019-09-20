package com.zhl.reflect.proxy.cglib;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/20 12:36
 */
public class UserDao {

    public boolean save(String name) {
        System.out.println("目标对象,保存成功：" + name);
        return true;
    }
}
