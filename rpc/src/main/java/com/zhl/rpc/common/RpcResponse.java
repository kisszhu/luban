package com.zhl.rpc.common;

/**
 * @program codeX
 * @description:RPC通信消息的响应数据规则
 * @author: meilong
 * @create: 2019/09/03 08:32
 */
public class RpcResponse {
    /**
     * 响应的消息ID
     */
    private String responseId;
    /**
     * 请求的消息ID
     */
    private String requestId;
    /**
     * 响应的消息是否成功
     */
    private boolean success;
    /**
     * 响应的数据结果
     */
    private Object result;
    /**
     * 响应的异常信息
     */
    private Throwable throwable;
}