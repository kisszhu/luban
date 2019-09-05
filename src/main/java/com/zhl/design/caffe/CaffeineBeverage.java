package com.zhl.design.caffe;

/**
 * Created by zhuhailong-dc on 2018/5/24.
 * 咖啡因
 */
public abstract class CaffeineBeverage {
    final void prepareRecipe() {
        /**
         * 模板方法定义了一个算法的步骤，并允许子类为一个或多个步骤提供实现
         * 在这个模板中，算法内的每一个步骤都被一个方法代表了
         * 某些方法由这个类处理，某些方法由这个类的子类处理
         */
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into cup");
    }
}
