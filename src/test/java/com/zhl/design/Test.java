package com.zhl.design;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by zhuhailong-dc on 2018/5/23.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.zhl.design.MyClass");
        for (Constructor method : clazz.getDeclaredConstructors()) {
            System.out.println(method.getName());
        }
        Constructor cons = clazz.getDeclaredConstructor();
        cons.setAccessible(true);
        cons.newInstance();
        //clazz.getDeclaredMethod("MyClass");
    }
}
