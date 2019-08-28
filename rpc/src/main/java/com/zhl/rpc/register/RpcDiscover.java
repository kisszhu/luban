package com.zhl.rpc.register;

import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * @program codeX
 * @description:用于获取服务地址
 * @author: meilong
 * @create: 2019/08/29 07:36
 */
public class RpcDiscover {
    private String registryAddress;

    private volatile List<String> dataList = new ArrayList<>();

    private ZooKeeper zooKeeper = null;
}

//package cn.wolfcode.rpc.register;
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//import org.apache.zookeeper.ZooKeeper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;@Setter@Getter
////地址发现,用于实时的获取最新的RPC服务信息
//public class RpcDiscover {
// public static final Logger LOGGER=LoggerFactory.getLogger(RpcRegistry.class);    //服务端地址 zkServer的地址
//private String registryAddress;    //获取到的所有提供服务的服务器列表
//private volatile List<String> dataList=new ArrayList<>();
// private ZooKeeper  zooKeeper=null;    //初始化zkClient客户端

//public RpcDiscover(String registryAddress) throws Exception {        this.registryAddress = registryAddress;
//        zooKeeper=new ZooKeeper(registryAddress, Constant.SESSION_TIMEOUT, new Watcher() {            @Override
//public void process(WatchedEvent watchedEvent) {                if(watchedEvent.getType()==Event.EventType.NodeChildrenChanged){                    //监听zkServer的服务器列表变化
//        watchNode();
//        }
//        }
//        });        //获取节点相关数据
//        watchNode();
//        }    // 从dataList列表随机获取一个可用的服务端的地址信息给rpc-client
//public String discover(){        int size=dataList.size();        if(size>0){            int index= new Random().nextInt(size);            return dataList.get(index);
//        }        throw new RuntimeException("没有找到对应的服务器");
//        }    //监听服务端的列表信息
//private void watchNode(){        try{            //获取子节点信息
//        List<String> nodeList = zooKeeper.getChildren(Constant.REGISTRY_PATH, true);
//        List<String> dataList=new ArrayList<>();            for (String node : nodeList) {                byte[] bytes = zooKeeper.getData(Constant.REGISTRY_PATH + "/" + node, false, null);
//        dataList.add(new String(bytes));
//        }            this.dataList=dataList;
//        }catch (Exception e){
//        LOGGER.error("",e);
//        e.printStackTrace();
//        }
//        }    //测试程序
//public static void main(String[] args) throws Exception {        //打印获取到的连接地址信息
//        System.out.println(new RpcDiscover("192.168.158.151:2181").discover());
//        System.in.read();
//        }
//        }Constant常量定义,设置连接ZKServer的相关参数RpcRegistry:往注册中心ZKServer设置地址信息,RPC-Server需要使用RpcDiscover: 从注册中心ZKServer获取服务端的网络地址信息 RPC-client需要使用
//        rpc-common
//        定义RPC通信的请求消息和响应消息的规则,以及消息的序列化和反序列化的帮助类,主要包括
//        具体代码如下