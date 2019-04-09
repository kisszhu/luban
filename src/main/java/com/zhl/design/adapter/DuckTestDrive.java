package com.zhl.design.adapter;

public class DuckTestDrive {
    public static void main(String[] args) {
        // 创建鸭子
        MallardDuck duck = new MallardDuck();
        // 创建火鸡
        WildTurkey turkey = new WildTurkey();

        // 然后将火鸡包装进一个火鸡适配器中，使它看起来像是一只鸭子
        Duck turkeyAdapter = new TurekeyAdapter(turkey);

        turkeyAdapter.quack();
        turkeyAdapter.fly();
    }
}
