package com.zhl.design.beverage;

/**
 * Created by zhuhailong-dc on 2018/5/13.
 * 调料-摩卡
 */
public class Mocha extends CondimentDecorator{
    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage=beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Mocha";
    }

    @Override
    public double cost() {
        return .20+beverage.cost();
    }
}
