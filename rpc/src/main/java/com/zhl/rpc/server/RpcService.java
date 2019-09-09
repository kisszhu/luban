package com.zhl.rpc.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program codeX
 * @description:RPC服务端，启动RPC服务，扫描app-server中所有可以提供的服务列表并保存。接受RPC客户端的消息并且通过反射调用具体的方法
 * @author: meilong
 * @create: 2019/09/07 09:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RpcService {
    Class<?> value();
}