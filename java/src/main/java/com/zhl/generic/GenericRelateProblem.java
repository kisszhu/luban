package com.zhl.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @program codeX
 * @description:泛型相关问题
 * @author: meilong
 * @create: 2019/08/16 14:49
 */
public class GenericRelateProblem {

    public static void main(String[] args) {
        /**
         * 1、为什么数组是协变的，而泛型是不变的？
         * 原因是每个数组在运行时都知道其元素类型，而泛型集合不知道。泛型进行了类型擦除
         */
        String[] strings = new String[2];
        // 编译成功
        Object[] objects = strings;

        List<String> stringList = new ArrayList<>();
        // 编译报错
        // List<Object> objectList = stringList;

        /**
         * 2、Array中可以使用泛型吗？
         * Array事实上并不支持泛型，Joshua Bloch在Effective Java一书中建议使用List来代替Array。
         * 因为List可以提供编译期的类型安全保证，而Array却不能。
         */
    }
}
