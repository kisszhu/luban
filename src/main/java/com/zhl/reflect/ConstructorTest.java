package com.zhl.reflect;

import java.lang.reflect.Constructor;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/08/15 07:26
 */
public class ConstructorTest {
    /**
     * 反射提供了两种创建对象的方式：
     * 1：获取实例对象
     * 获取类对象 Class clazz = Class.forName("Entity");
     * 获取构造器对象 Constructor cons = clazz.getConstructor(形参.class)
     * 获取对象 Entity entity = cons.newInstance(实参)
     * <p>
     * 2：获取泛型对象(详见：BeanMapper.java)
     * 获取类对象 Class<E> class =
     * 获取泛型对象 E target = class.newInstance()
     */
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.zhl.reflect.Entity");

        System.out.println("所有公有函数：");
        Constructor[] publicCons = clazz.getConstructors();
        for (Constructor cons : publicCons) {
            System.out.println(cons);
        }

        System.out.println("所有函数：");
        Constructor[] allCons = clazz.getDeclaredConstructors();
        for (Constructor cons : allCons) {
            System.out.println(cons);
        }

        System.out.println("获取公有无参构造函数：");
        Constructor cons = clazz.getConstructor(null);
        System.out.println(cons);

        System.out.println("获取私有构造函数");
        Constructor privateCons = clazz.getDeclaredConstructor(Long.class, String.class, Integer.class);
        System.out.println(privateCons);
        privateCons.setAccessible(true);
        Entity entity = (Entity) privateCons.newInstance(1L, "alan", 18);
        System.out.println("年龄：" + entity.getAge());
    }
}

class Entity {
    public Long id;
    private String name;
    private Integer age;

    public Entity() {

    }

    public Entity(Long id) {
        this.id = id;
    }

    protected Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Entity(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
