package com.zhl.io.serial.java.serializable;

import java.io.Serializable;

/**
 * @program codeX
 * @description:用于序列化的实例
 * @author: meilong
 * @create: 2019/08/24 11:15
 */
public class User implements Serializable {

    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
