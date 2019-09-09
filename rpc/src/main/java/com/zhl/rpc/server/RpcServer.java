package com.zhl.rpc.server;

import com.zhl.rpc.common.RpcDecoder;
import com.zhl.rpc.common.RpcEncoder;
import com.zhl.rpc.common.RpcRequest;
import com.zhl.rpc.common.RpcResponse;
import com.zhl.rpc.register.RpcRegistry;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * @program codeX
 * @description:Rpc服务端
 * @author: meilong
 * @create: 2019/09/09 07:25
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RpcServer implements ApplicationContextAware, InitializingBean {
    /**
     * 用于存储所有提供服务的类
     */
    private final Map<String, Object> serviceBeans = new HashMap<>(16);

    /**
     * 用于服务的注册
     */
    private RpcRegistry rpcRegistry;

    /**
     * 服务注册地址
     */
    private String serverAddress;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object object : serviceBeanMap.values()) {
                String serviceName = object.getClass().getAnnotation(RpcService.class).value().getName();
                this.serviceBeans.put(serviceName, object);
            }
        }
        System.out.println("服务器：" + serverAddress + " 提供的服务列表：" + serviceBeans);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 创建服务端的通信对象
        ServerBootstrap server = new ServerBootstrap();
        // 创建异步通信的事件组，用于建立TCP连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 创建异步通信的事件组，用于处理Channel的I/O事件
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // TODO 传入RpcRequest RpcResponse exist ambiguity
                            ch.pipeline().addLast(new RpcDecoder(RpcRequest.class))
                                    .addLast(new RpcEncoder(RpcResponse.class))
                                    .addLast(new RpcServerHandler(serviceBeans));
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            String host = serverAddress.split(":")[0];
            int port = Integer.valueOf(serverAddress.split(":")[1]);
            ChannelFuture future = server.bind(host, port).sync();

            // TODO for rpc registry purpose
            rpcRegistry.createNode(serverAddress);
            future.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
