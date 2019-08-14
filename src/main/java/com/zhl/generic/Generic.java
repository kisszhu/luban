package com.zhl.generic;


import java.util.ArrayList;
import java.util.List;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/06/20 18:30
 */
public class Generic {

    static class GenericClass<T> {
        /**
         * value的类型是泛型
         */
        private T value;

        public GenericClass(T value) {
            this.value = value;
        }
    }

    static class NormalClass {
        public <T> void genericMethod(T perm) {
            System.out.println(perm);
        }
    }

    /**
     * 大家都知道数组是协变的，这就意味着如果一个方法的入参是Object[]类型，我传入Integer[]类型的变量也是合法的
     * 但是泛型就不一样了，它是非协变的。
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] array = new Integer[10];
        List<Integer> list = new ArrayList<>();
        f1(array);
        // compile error
        // f2(list);

        // 通过通配符 解决f2函数报错的问题
        Object obj = new Object();
        Man man = new Man();
        Human hu = new Human();

        List<?> li = new ArrayList<>();
        // compile error
        //li.add(obj);
        // compile error
        //li.add(man);
        // compile error
        //li.add(hu);
        // compile success
        Object o = li.get(0);
        // compile error
        //Man m = li.get(0);


        List<? extends Human> li2 = new ArrayList<>();
        // compile error
        //li2.add(obj);
        // compile error
        //li2.add(man);
        // compile error
        //li2.add(hu);
        // compile success
        Object o2 = li2.get(0);
        // compile success
        Human hu2 = li2.get(0);

        List<? super Human> li3 = new ArrayList<>();
        // compile error
        //li3.add(obj);
        // compile error
        //li3.add(man);
        // compile success
        li3.add(hu);
        // compile success
        Object o3 = li3.get(0);
        // compile error
        //Human hu3 = li3.get(0);

    }

    public static void f1(Object[] array) {

    }

    public static void f2(List<Object> list) {

    }
}

class Man {

}

class Human extends Man {

}
