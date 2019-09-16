package com.zhl.rpc.common;

/**
 * @program codeX
 * @description:RPC通信的数据请求规则
 * @author: meilong
 * @create: 2019/09/03 08:14
 */
public class RpcRequest {
    /**
     * 请求消息的消息ID
     */
    private String requestId;
    /**
     * 请求的具体类名
     */
    private String className;
    /**
     * 请求的具体函数名
     */
    private String methodName;
    /**
     * 请求的方法参数类型列表
     */
    private Class<?>[] parameterTypes;
    /**
     * 请求的方法参数列表
     */
    private Object[] parameters;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}