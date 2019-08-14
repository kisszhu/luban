package com.zhl.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/08/14 20:20
 */
public class BeanMapper {

    public static <T, E> List<E> copyProp(List<T> sources, Class<E> tClass) throws Exception {
        List<E> list = new ArrayList<>();
        for (T source : sources) {
            // 获取源对象的类的详细信息
            Class<?> sClass = source.getClass();

            // 获取源对象的所有属性
            Field[] sFields = sClass.getDeclaredFields();
            // 获取目的对象的所有属性
            Field[] tFields = tClass.getDeclaredFields();

            E target;
            try {
                target = tClass.newInstance();
            } catch (Exception e) {
                throw e;
            }

            for (Field s : sFields) {
                for (Field t : tFields) {

                    if (s.getName().equals(t.getName()) && s.getGenericType().equals(t.getGenericType())) {
                        try {
                            String sName = s.getName();
                            char[] sChars = sName.toCharArray();
                            sChars[0] -= 32;

                            String sMethodName = "get" + String.valueOf(sChars);
                            // 获得属性的get方法
                            Method sMethod = sClass.getMethod(sMethodName);
                            Object sFieldValue = sMethod.invoke(source);
                            
                            String tName = t.getName();
                            char[] tChars = tName.toCharArray();
                            tChars[0] -= 32;

                            String tMethodName = "get" + String.valueOf(tChars);
                            Method tMethod = tClass.getMethod(tMethodName, t.getType());

                            tMethod.invoke(target, sFieldValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            list.add(target);
        }
        return list;
    }
}