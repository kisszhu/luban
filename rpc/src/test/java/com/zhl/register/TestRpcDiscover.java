package com.zhl.register;

import com.zhl.rpc.register.RpcDiscover;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/03 08:06
 */
public class TestRpcDiscover {
    public static void main(String[] args) throws Exception {
        System.out.println(new RpcDiscover("192.168.1.66:2181").discover());
    }
}