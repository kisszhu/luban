package com.zhl.design.beverage;

/**
 * Created by zhuhailong-dc on 2018/5/13.
 * 继承类型的抽象类、也可以是接口类型
 */
public abstract class Beverage {
    String description="Unknown Beverage";
    public String getDescription(){
        return description;
    }
    public abstract double cost();
}
