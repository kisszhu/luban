package com.zhl.rpc.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/09 08:06
 */
public class RpcServerHandler extends ChannelInboundHandlerAdapter {
    public RpcServerHandler(Map<String,Object> serviceBeans){

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
