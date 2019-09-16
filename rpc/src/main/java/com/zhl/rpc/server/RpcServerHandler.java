package com.zhl.rpc.server;

import com.zhl.rpc.common.RpcRequest;
import com.zhl.rpc.common.RpcResponse;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/09/09 08:06
 */
public class RpcServerHandler extends ChannelInboundHandlerAdapter {
    private Map<String, Object> serviceBeanMap;

    public RpcServerHandler(Map<String, Object> serviceBeans) {
        this.serviceBeanMap = serviceBeans;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("RpcServerHandler.channelRead");
        System.out.println(msg);
        RpcRequest rpcRequest = (RpcRequest) msg;
        RpcResponse rpcResponse = handler(rpcRequest);
        ctx.writeAndFlush(rpcResponse)
                // 告诉客户端,关闭SOCKET连接
                .addListener(ChannelFutureListener.CLOSE);
        super.channelRead(ctx, msg);
    }

    private RpcResponse handler(RpcRequest request) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setResponseId(UUID.randomUUID().toString());
        rpcResponse.setRequestId(request.getRequestId());

        String className = request.getClassName();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod(methodName, parameterTypes);
            if (method == null) {
                throw new RuntimeException("没有找到对应的函数");
            }

            Object serviceBean = serviceBeanMap.get(className);
            if (serviceBean == null) {
                throw new RuntimeException(className + "没有对应的serviceBean:" + className + ":beanMap:" + serviceBeanMap);
            }

            Object result = method.invoke(serviceBean, parameters);
            rpcResponse.setSuccess(true);
            rpcResponse.setResult(result);
        } catch (Exception ex) {
            rpcResponse.setSuccess(false);
            rpcResponse.setThrowable(ex);
            ex.printStackTrace();
        }
        return rpcResponse;
    }
}
