package com.zhl.io.serial.java.externalizable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/08/24 12:01
 */
public class ExtSerialDemo {
    public static void main(String[] args) throws Exception {
        User user = new User("张三", 12, "12345");

        FileOutputStream fos = new FileOutputStream("user.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(user);

        FileInputStream fis = new FileInputStream("user.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User obj = (User) ois.readObject();

        System.out.println(obj.getName());
        System.out.println(obj.getAge());
    }
}
