package com.zhl.design.beverage;

/**
 * Created by zhuhailong-dc on 2018/5/13.
 * House Blend Coffee
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description="House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
