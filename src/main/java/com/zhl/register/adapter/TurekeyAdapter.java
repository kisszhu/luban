package com.zhl.register.adapter;

/**
 * 首先，你需要实现想转换成的类型接口，也就是你的客户所期望看到的接口
 */
public class TurekeyAdapter implements Duck {

    Turkey turkey;

    /**
     * 接着，需要取得要适配的对象引用，这里我们利用构造器取得这个引用
     */
    public TurekeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    /**
     * 现在我们要实现接口中所有的方法
     */
    public void quack() {
        turkey.gobble();
    }

    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
