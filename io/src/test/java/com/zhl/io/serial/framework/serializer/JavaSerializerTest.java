package com.zhl.io.serial.framework.serializer;

import com.zhl.io.serial.java.serializable.User;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/05 08:54
 */
public class JavaSerializerTest {

    public static void main(String[] args) {
        JavaSerializer serializer = new JavaSerializer();
        User user = new User("张三", "123");
        byte[] bytes = serializer.serialize(user);

        user.setUserName("李四");
        System.out.println("before the user name is " + user.getUserName());

        User user2 = serializer.deserialize(bytes, User.class);
        System.out.println("deserialize the user name is " + user2.getUserName());
    }
}
