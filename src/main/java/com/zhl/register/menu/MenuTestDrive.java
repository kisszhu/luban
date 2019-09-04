package com.zhl.register.menu;

/**
 * 测试类
 */
public class MenuTestDrive {
    public static void main(String args[]) {
        MenuComponent pancake = new Menu("PANKCAKE HOUSE MENU", "Breakfast");
        MenuComponent dinner = new Menu("DINER MENU", "Lunch");
        MenuComponent cafe = new Menu("CAFE MENU", "Dinner");

        MenuComponent all = new Menu("ALL", "All menus");
        all.add(pancake);
        all.add(dinner);
        all.add(cafe);

        // 加入菜单项
        dinner.add(new MenuItem("Pasta", "with sauce", true, 3.7));

        Waitress waitress = new Waitress(all);
        waitress.printMenu();
    }
}
