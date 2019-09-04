package com.zhl.register.pizza;

import com.zhl.register.pizza.ingredient.*;

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
