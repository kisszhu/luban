package com.zhl.rpc.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @program codeX
 * @description:对传递的消息进行编码，因为是请求/响应对象的传递，先编码为字节数组在发送到服务端解码
 * @author: meilong
 * @create: 2019/09/04 07:47
 */
public class RpcEncoder extends MessageToByteEncoder {
    private Class genericClass;

    public RpcEncoder(Class genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (genericClass.isInstance(msg)) {
            byte[] bytes = SerializationUtil.serialize(msg);
            out.writeBytes(bytes);
        }
    }
}