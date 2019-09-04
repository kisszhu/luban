package com.zhl.register.pizza;

/**
 * Created by zhuhailong-dc on 2018/5/22.
 */
public class PizzaTestDrive {
    public static void main(String[] args){
        PizzaStore nyStore=new NYPizzaStore();
        Pizza pizza=nyStore.orderPizza("cheese");
        System.out.println(pizza.getName());
    }
}
