package com.zhl.design.pizza;

/**
 * Created by zhuhailong-dc on 2018/5/22.
 * 披萨工厂抽象类
 */
public abstract class PizzaStore {

    public Pizza orderPizza(String type){
        Pizza pizza;
        pizza=createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    protected abstract Pizza createPizza(String type);
}
