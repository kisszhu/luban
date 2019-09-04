package com.zhl.register.composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 现在我们必须确定所有实现Quackable的具体类都能够扮演QuackObservable的角色
 * 我们需要在每一个类中实现注册和通知。但是这次我们要用稍微不一样的做法：我们要在另一个被称为
 * Observable的类中封装注册和通知的代码，然后将它和QuackObservable组合在一起。这样我们只需要
 * 一份代码即可。
 */
public class Observable implements QuackObservable {
    ArrayList observers = new ArrayList();
    QuackObservable duck;

    public Observable(QuackObservable duck) {
        this.duck = duck;
    }

    public void registerObserver(Observer duck) {
        observers.add(duck);
    }

    public void notifyObservers() {
        Iterator iterator = observers.iterator();
        while (iterator.hasNext()) {
            Observer observer = (Observer) iterator.next();
            observer.update(duck);
        }
    }
}
