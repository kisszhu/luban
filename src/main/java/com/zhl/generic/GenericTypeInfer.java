package com.zhl.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @program codeX
 * @description:泛型类型推测
 * @author: meilong
 * @create: 2019/08/16 14:21
 */
public class GenericTypeInfer {

    public static void main(String[] args) {
        /**
         * 1、泛型类型推测
         */
        // (int,int) -> int
        int x = add(1, 1);
        // (int,float) -> number
        Number y = add(1, 1.2);
    }

    /**
     * 如果x,y的类型为相同类型的话，如int,int 则返回类型为int
     * 如果x,y的类型为不同类型的话，如int,float，则返回类型为它们的最小父类
     *
     * @param x
     * @param y
     * @param <T>
     * @return
     */
    public static <T> T add(T x, T y) {
        return x;
    }

}
