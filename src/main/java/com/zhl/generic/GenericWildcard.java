package com.zhl.generic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @program codeX
 * @description:泛型-通配符
 * @author: meilong
 * @create: 2019/08/16 15:22
 */
public class GenericWildcard {

    /**
     * 对于频繁读取，不写入的用extends
     * 对于频繁写入，用super
     * <p>
     * 以？和? extends T声明的集合，因为无法知悉它需要的具体的子类型，所以不能往此集合中添加元素，但是可以获取
     * 以? super T 声明的集合，传入的只要是T及其子类，就可以添加，但是只能用Object类型接收
     */
    public static void main(String[] args) {
        testWildcardExtend();
        testWildcardSuper();

        ArrayList<String> arrayList = new ArrayList<>();
        // 编译报错
        // if (arrayList instanceof ArrayList<String>) {}
        if (arrayList instanceof ArrayList<?>) {

        }
    }

    public static void testWildcardSuper() {
        List<? super Number> list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Float(1.1));

    }

    public static void fillNumberList(List<? super Number> list) {
        list.add(new Integer(0));
        list.add(new Float(1.0));
    }

    public static void testWildcardExtend() {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        print(intList);

        List<Float> floatList = new ArrayList<>();
        floatList.add(1.1f);
        floatList.add(1.2f);
        print(floatList);

        /**
         * 语句A是编译成功的
         * 语句B是编译失败的
         * 那么为什么语句B会是编译失败的？打一个比方，如果B编译不报错，这个时候把A语句的注释打开，则list其实是float类型，导致B的出现类型不匹配问题。
         * 所以List<? extends T> 禁止添加任何对象
         */
        List<? extends Number> list = new ArrayList<>();
        // list = new ArrayList<Float>(); // A
        // 编译报错
        // list.add(new Integer(1)); // B
    }

    public static void print(List<? extends Number> list) {
        for (Number number : list) {
            System.out.println(number.intValue());
        }
        System.out.println("-------------------");
    }
}
