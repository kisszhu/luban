package com.zhl.register.menu;

import java.util.Iterator;

/**
 * 空迭代器
 */
public class NullIterator implements Iterator {
    public Object next() {
        return null;
    }

    public boolean hasNext() {
        return false;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
