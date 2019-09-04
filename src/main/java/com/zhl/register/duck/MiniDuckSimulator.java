package com.zhl.register.duck;

/**
 * Created by zhuhailong-dc on 2018/5/1.
 * -
 */
public class MiniDuckSimulator {
    public static void main(String[] args){
//        Duck mallard=new MallardDuck();
        Duck mallard=new ModelDuck();
        mallard.performQuack();
        mallard.setFlyBehaviro(new FlyRocketPowered());
        mallard.performFly();
    }
}
