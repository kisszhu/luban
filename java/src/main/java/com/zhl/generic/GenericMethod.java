package com.zhl.generic;

/**
 * @program codeX
 * @description:泛型函数 & 泛型与静态函数
 * @author: meilong
 * @create: 2019/08/16 14:53
 */
public class GenericMethod {
}

class Test<T> {

    /**
     * 在code 与 getCode中，T 均是来自 Test<T>。关键点就是Test<T>中的T只能是在创建对象之后才有效，所以code getCode不能是static描述的
     */

    // 编译报错
    // public static T code;

    // 编译报错
    // public static T getCode(T one) {
    //     return one;
    // }

    /**
     * note：这种写法是ok的，定义了一个泛型函数，函数中的T不是来自于 Test<T>，而是来自于函数中的 <T>
     *
     * @param one
     * @param <T>
     * @return
     */
    public static <T> T getCode(T one) {

        return one;
    }
}

