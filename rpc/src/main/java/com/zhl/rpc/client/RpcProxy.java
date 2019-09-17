package com.zhl.rpc.client;

import com.zhl.rpc.common.RpcRequest;
import com.zhl.rpc.common.RpcResponse;
import com.zhl.rpc.register.RpcDiscover;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @program codeX
 * @description:动态代理类，用于获取到每个类的代理对象
 * @author: meilong
 * @create: 2019/09/16 08:51
 */
public class RpcProxy {
    private RpcDiscover rpcDiscover;

    /**
     * RpcProxy对于每一个类都创建一个动态代理对象，并且在invoke方法创建rpc客户端并且发送网络通信请求
     *
     * @param interfaceClass
     * @param <T>
     * @return
     */
    public <T> T getInstance(Class<T> interfaceClass) {
        T instance = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                RpcRequest rpcRequest = new RpcRequest();
                String className = method.getDeclaringClass().getName();
                Class<?>[] parameterTypes = method.getParameterTypes();

                rpcRequest.setRequestId(UUID.randomUUID().toString());
                rpcRequest.setClassName(className);
                rpcRequest.setParameterTypes(parameterTypes);
                rpcRequest.setParameters(args);
                rpcRequest.setMethodName(method.getName());
                RpcResponse rpcResponse = new RpcClient(rpcRequest, rpcDiscover).send();
                return rpcResponse.getResult();
            }
        });
        // 返回一个代理对象
        return instance;
    }

    public RpcDiscover getRpcDiscover() {
        return rpcDiscover;
    }

    public void setRpcDiscover(RpcDiscover rpcDiscover) {
        this.rpcDiscover = rpcDiscover;
    }
}
