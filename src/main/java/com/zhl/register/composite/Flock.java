package com.zhl.register.composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 组合模式+迭代器模式
 * 在DuckSimulator要分别管理这些不同的鸭子变得有些困难了，你能够帮我们作为
 * 一个整体来管理这些鸭子。
 * 甚至让我们管理几个想持续追踪的鸭子家族吗？---树状结构，组合模式
 * //  Quackable mallardDuck = duckFactory.createMallardDuck();
 * //    Quackable redheadDuck = duckFactory.createRedHeadDuck();
 * //    Quackable duckCall = duckFactory.createDuckCall();
 * //    Quackable rubberDuck = duckFactory.createRubberDuck();
 * //
 * //    // 适配器模式
 * //    Quackable gooseDuck = new GooseAdapter(new Goose());
 * //    simulate(mallardDuck);
 * //    simulate(redheadDuck);
 * //    simulate(duckCall);
 * //    simulate(rubberDuck);
 * //    simulate(gooseDuck);
 */
public class Flock implements Quackable {
    /**
     * 别忘了，组合需要和叶节点一样实现相同的接口。这里的“叶节点”就是Quackable
     * 在每一个Flock内，我们使用ArrayList记录属于这个Flock的Quackable对象
     */
    ArrayList quackers = new ArrayList();

    /**
     * 你或许还记得，在组合模式中，组合（菜单）和叶节点（菜单项）具有一组相同的方法，其中
     * 包括了add()方法。就因为有一组相同的方法，我们才能在菜单项上调用不起作用的方法。这么设计
     * 的好处是，叶节点和组合之间是透明的。客户根本不用管究竟是组合还是叶节点，客户只是调动
     * 两个的同一个方法。
     * 但是在这里，我们决定把组合维护孩子的方法和叶节点分开，也就是说，我们打算只让Flock具有add()方法
     * 。我们知道给Duck添加某些东西是无意义的。这样的设计比较安全，你不会调用无意义的方法，但是透明性较差
     * 。现在，客户如果想要调用add()，得先确定Quackable对象是Flock才行。
     * 在OO设计过程中，折中一直是避免不了的，在创建你自己的组合时，你需要考虑这些。
     */
    public void add(Quackable quackable) {
        quackers.add(quackable);
    }

    public void quack() {
        // 迭代器模式
        Iterator iterator = quackers.iterator();
        while (iterator.hasNext()) {
            Quackable quackable = (Quackable) iterator.next();
            quackable.quack();
        }
    }

    Observable observable;

    public Flock() {
        observable = new Observable(this);
    }

    public void registerObserver(Observer observer) {
        Iterator iterator = quackers.iterator();
        while (iterator.hasNext()) {
            Quackable quackable = (Quackable) iterator.next();
            quackable.registerObserver(observer);
        }
        observable.registerObserver(observer);
    }

    public void notifyObservers() {
        observable.notifyObservers();
    }
}
