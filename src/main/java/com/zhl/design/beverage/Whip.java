package com.zhl.design.beverage;

/**
 * Created by zhuhailong-dc on 2018/5/13.
 * 调料-Whip
 */
public class Whip extends CondimentDecorator{
    Beverage beverage;

    public Whip(Beverage beverage){
        this.beverage=beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+", Whip";
    }

    @Override
    public double cost() {
        return .30+beverage.cost();
    }
}
