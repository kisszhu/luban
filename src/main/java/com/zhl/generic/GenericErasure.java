package com.zhl.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @program codeX
 * @description:描述泛型擦数
 * @author: meilong
 * @create: 2019/08/16 14:00
 */
public class GenericErasure {

    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        list.getClass().getMethod("add", Object.class).invoke(list, "张三");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // 1
        // 张三

        /**
         * 运行上面的代码，根据运行结果，可以得出"张三"是可以插入List<Integer>中的。原理就是泛型在编译期的时候进行了擦数
         * 在List<E>中，E是一个无限定的类型变量，所以用Object替换。
         */
    }
}
