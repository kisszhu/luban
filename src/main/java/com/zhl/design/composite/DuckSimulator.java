package com.zhl.design.composite;

public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();
        simulator.simulate(duckFactory);
    }

    /**
     * 我们创建一个多态的方法，此方法需要一个用来创建对象的工厂。
     * 通过传入不同的工厂，我们就会得到不同的产品家族
     */
    void simulate(AbstractDuckFactory duckFactory) {
        // 下面我们把创建和装饰的部分包装起来：工厂模式
        Quackable mallardDuck = duckFactory.createMallardDuck();// new QuackCounter(new MallardDuck());
        Quackable redheadDuck = duckFactory.createRedHeadDuck();// new QuackCounter(new RedheadDuck());
        Quackable duckCall = duckFactory.createDuckCall();// new QuackCounter(new DuckCall());
        Quackable rubberDuck = duckFactory.createRubberDuck();// new QuackCounter(new RubberDuck());

        // 适配器模式
        Quackable gooseDuck = new GooseAdapter(new Goose());

        // 组合模式
        Flock flockOfDucks = new Flock();
        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(gooseDuck);

        Flock flockOfMallards = new Flock();
        flockOfMallards.add(duckFactory.createMallardDuck());
        flockOfMallards.add(duckFactory.createMallardDuck());
        flockOfMallards.add(duckFactory.createMallardDuck());
        flockOfMallards.add(duckFactory.createMallardDuck());

        flockOfDucks.add(flockOfMallards);

        // 观察者模式
        Quackologist quackologist = new Quackologist();
        flockOfDucks.registerObserver(quackologist);

        System.out.println("Duck Simulator: Whole Flock Simulation");
        simulate(flockOfDucks);

        System.out.println("Duck Simulator：Mallard Flock Simulation");
        simulate(flockOfMallards);


//        System.out.println("Duck Simulator");
//        simulate(mallardDuck);
//        simulate(redheadDuck);
//        simulate(duckCall);
//        simulate(rubberDuck);
//        simulate(gooseDuck);
//
        System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times");
    }

    void simulate(Quackable duck) {
        duck.quack();
    }
}
