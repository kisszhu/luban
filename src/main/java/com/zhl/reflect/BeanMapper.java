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

    public static void main(String[] args) throws Exception {
        List<Po> poList = new ArrayList<>();
        Po po = new Po(1L, "Alan");
        poList.add(po);

        List<Dto> dtoList = copyProp(poList, Dto.class);
        for (Dto dto : dtoList) {
            System.out.println(dto.getId());
            System.out.println(dto.getName());
        }
    }

    public static <T, E> List<E> copyProp(List<T> sources, Class<E> tClass) throws Exception {
        List<E> list = new ArrayList<>();
        for (T source : sources) {
            /**
             * 获取类对象
             * 1、Class.forName(className)
             * 2、Po.class
             * 3、new Po().getClass()
             */
            Class<?> sClass = source.getClass();

            // 获取来源对象的所有属性
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

                            String tMethodName = "set" + String.valueOf(tChars);
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

class Po {
    private Long id;
    private String name;

    public Po() {
    }

    public Po(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Dto {
    private Long id;
    private String name;

    public Dto() {
    }

    public Dto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}