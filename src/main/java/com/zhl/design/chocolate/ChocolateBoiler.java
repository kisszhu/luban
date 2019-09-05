package com.zhl.design.chocolate;

/**
 * Created by zhuhailong-dc on 2018/5/23.
 * 巧克力工厂
 */
public class ChocolateBoiler {
    private boolean empty;
    private boolean boiled;

    public ChocolateBoiler() {
        // 代码开始时锅炉是空的
        empty = false;
        boiled = false;
    }

    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
            // 在锅炉内填满巧克力和牛奶的混合物
        }
    }

    public void drain() {
        if (!isEmpty() && isBoiled()) {
            empty = true; // 排出煮沸的巧克力和牛奶
        }
    }

    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            // 将炉内物煮沸
            boiled = true;
        }
    }


    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return isBoiled();
    }


}
