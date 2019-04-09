package com.zhl.design.menu;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/**
 * 菜单的客户
 */
public class Waitress {
    MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        Iterator iterator = allMenus.createIterator();
        System.out.println("VEGETARIAN MENU ----");
        while (iterator.hasNext()) {
            MenuComponent menuComponent = (MenuComponent) iterator.next();
            try {
                if (menuComponent.isVegetarian()) {
                    menuComponent.print();
                }
            } catch (UnsupportedOperationException e) {
            }
        }
        allMenus.print();
    }
}
