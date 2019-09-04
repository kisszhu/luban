package com.zhl.register.menu;

import java.util.Iterator;

/**
 * 菜单组件.
 * 一开始，我们需要创建一个组件接口来作为菜单和菜单项的共同接口，让我们能够用
 * 统一的做法来处理菜单和菜单项
 * <p>
 * 请记住，菜单组件的角色是为叶节点和组合节点提供一个共同的接口。
 * 现在你可能想问：那么菜单组件不就扮演了两个角色吗？
 * 可能是这样的，我们稍后在讨论这一点
 */
public abstract class MenuComponent {
    /**
     * 所有的组件都必须实现MenuComponent接口，然后，叶节点和组合节点的角色不同，
     * 所以有些方法可能并不适合某些节点。面对这种情况，有时候，你最好是抛出运行时异常。
     */
    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    public void print() {
        throw new UnsupportedOperationException();
    }

    public abstract Iterator createIterator();
}
