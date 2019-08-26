package com.zhl.concurrency.chapter4;

import java.util.HashSet;
import java.util.Set;

/**
 * 实例封闭.
 */
public class PersonSet {

    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

}

class Person {

}