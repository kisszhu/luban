package com.zhl.app.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/17 07:36
 */
public class BootAppServer {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:rpc-application.xml");
    }
}