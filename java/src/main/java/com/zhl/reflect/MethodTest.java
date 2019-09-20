package com.zhl.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/08/15 08:27
 */
public class MethodTest {

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.zhl.reflect.Bo");
        // 在Bo中必须要显示声明无参构造函数
        Constructor cons = clazz.getConstructor(null);
        Bo bo = (Bo) cons.newInstance();

        Field field = clazz.getField("id");
        char[] charField = field.getName().toCharArray();
        charField[0] -= 32;

        String setName = "set" + String.valueOf(charField);
        Method setMethod = clazz.getDeclaredMethod(setName, Long.class);
        setMethod.setAccessible(true);
        setMethod.invoke(bo, 1L);

        String getName = "get" + String.valueOf(charField);
        Method getMethod = clazz.getDeclaredMethod(getName);
        getMethod.setAccessible(true);
        Object obj = getMethod.invoke(bo);
        System.out.println("id 取值为：" + obj);
    }
}

class Bo {
    public Long id;

    public Bo() {

    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
}

