package com.zhl.register.beverage;

/**
 * Created by zhuhailong-dc on 2018/5/13.
 * 浓缩咖啡
 */
public class Espresso extends Beverage {
    public Espresso(){
        description="Espresso";
    }
    @Override
    public double cost() {
        return 1.99;
    }
}
