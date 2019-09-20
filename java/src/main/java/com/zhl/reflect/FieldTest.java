package com.zhl.reflect;

import java.lang.reflect.Field;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/08/15 08:06
 */
public class FieldTest {

    /**
     * getField和getDeclaredField的区别：
     * getField只能获取public的字段，包括从父类继承来的字段（父类的字段也必须是public的）
     * getDeclaredField可以获取本类所有的字段，包括private的，但是不能获取继承来的字段
     */
    public static void main(String[] args) throws Exception {
        Pojo pojo = new Pojo();
        System.out.println("获取公有字段&继承来的字段:");
        Field[] fields = pojo.getClass().getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("获取本类的全部字段:");
        Field[] fields2 = pojo.getClass().getDeclaredFields();
        for (Field field : fields2) {
            System.out.println(field);
        }

        System.out.println("设置height字段的取值");
        Field field = pojo.getClass().getDeclaredField("height");
        field.setAccessible(true);
        field.set(pojo, 12D);
        System.out.println(pojo.getHeight());
    }
}

class Pojo extends Entity {
    private Double height;
    public Double weight;

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
