package com.zhl.rpc.client;

import com.zhl.rpc.common.RpcDecoder;
import com.zhl.rpc.common.RpcEncoder;
import com.zhl.rpc.common.RpcRequest;
import com.zhl.rpc.common.RpcResponse;
import com.zhl.rpc.register.RpcDiscover;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program codeX
 * @description: RPC客户端，通过网络通信往RPC服务端发送请求调用信息，接受服务端的响应消息，配置动态代理类
 * @author: meilong
 * @create: 2019/09/16 08:14
 */
public class RpcClient extends SimpleChannelInboundHandler<RpcResponse> {
    private RpcResponse rpcResponse;

    private RpcRequest rpcRequest;

    private Object object = new Object();

    private RpcDiscover rpcDiscover;

    public RpcClient(RpcRequest rpcRequest, RpcDiscover rpcDiscover) {
        this.rpcRequest = rpcRequest;
        this.rpcDiscover = rpcDiscover;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
        this.rpcResponse = msg;
        // 同步锁,资源对象
        synchronized (object) {
            ctx.flush();
            object.notifyAll();
        }
    }

    public RpcResponse send() throws Exception {
        Bootstrap client = new Bootstrap();
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        client.group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new RpcEncoder(RpcRequest.class))
                                .addLast(new RpcDecoder(RpcResponse.class))
                                // .....
                                .addLast(RpcClient.this);

                    }
                }).option(ChannelOption.SO_KEEPALIVE, true);

        // 获取一个服务器地址
        String serverAddress = rpcDiscover.discover();
        String host = serverAddress.split(":")[0];
        int port = Integer.valueOf(serverAddress.split(":")[1]);
        ChannelFuture future = client.connect(host, port).sync();

        System.out.println("客户端准备发送数据:" + rpcRequest);
        future.channel().writeAndFlush(rpcRequest).sync();

        synchronized (object) {
            object.wait();
        }
        if (rpcResponse != null) {
            future.channel().closeFuture().sync();
        }
        return rpcResponse;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}