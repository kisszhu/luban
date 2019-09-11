package com.zhl.io.serial.jackson;

/**
 * @program codeX
 * @description:序列化的类
 * @author: meilong
 * @create: 2019/09/10 19:51
 */
public class Person {
    private String name;

    private String address;

    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAbc() {
        return "abc";
    }

    public void setAbc(String abc) {

    }
}
