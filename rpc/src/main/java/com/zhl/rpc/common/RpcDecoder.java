package com.zhl.rpc.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/04 07:56
 */
public class RpcDecoder extends ByteToMessageDecoder {
    private Class genericClass;

    public RpcDecoder(Class genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO 什么是read able bytes 是一次全部读取完吗？
        int size = in.readableBytes();
        // TODO 为什么小于4，就能包装所有的消息都读取完
        if (size < 4) {
            return;
        }
        byte[] bytes = new byte[size];
        in.readBytes(bytes);
        Object obj = SerializationUtil.deserialize(bytes, genericClass);
        out.add(obj);
        // TODO 刷新缓存的目的
        ctx.flush();
    }
}
