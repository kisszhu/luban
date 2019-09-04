package com.zhl.register.composite;

/**
 * 此工厂创建没有装饰者的鸭子
 */
public class DuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createDuckCall() {
        return new DuckCall();
    }

    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }

    @Override
    public Quackable createRedHeadDuck() {
        return new RedheadDuck();
    }

    @Override
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }
}
