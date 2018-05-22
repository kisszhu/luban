package com.zhl.design.pizza;

import com.zhl.design.pizza.ingredient.*;

/**
 * Created by zhuhailong-dc on 2018/5/22.
 * 原料工厂
 */
public interface PizzaIngredientFactory {

    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clams createClam();

}
