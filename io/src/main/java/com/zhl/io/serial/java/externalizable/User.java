package com.zhl.io.serial.java.externalizable;

import java.io.*;

/**
 * @program codeX
 * @description:外部序列化
 * @author: meilong
 * @create: 2019/08/24 11:53
 */
public class User implements Externalizable {

    private String name;

    /**
     * 使用Externalizable的方式，可以打破transient的限制，但是并不建议这么做。
     * 既然设置了transient，就不要再去做打破它的事情，防止前后行为的矛盾
     */
    transient private Integer age;

    private String password;

    /**
     * 必须存在无参public 构造函数
     */
    public User() {
    }

    public User(String name, Integer age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = (Integer) in.readObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
