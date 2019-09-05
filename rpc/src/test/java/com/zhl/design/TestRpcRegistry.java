package com.zhl.design;

import com.zhl.rpc.register.RpcRegistry;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/08/27 09:40
 */
public class TestRpcRegistry {

    public static void main(String[] args) throws Exception {
        RpcRegistry registry = new RpcRegistry();
        registry.setConnect("192.168.1.66:2181");
        registry.createNode("test");
        System.in.read();
    }
}
