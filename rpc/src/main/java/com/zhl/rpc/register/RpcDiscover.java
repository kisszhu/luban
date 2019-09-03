package com.zhl.rpc.register;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    public RpcDiscover(String registryAddress) throws Exception {
        this.registryAddress = registryAddress;
        zooKeeper = new ZooKeeper(registryAddress, Constant.SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                    //TODO 事件重新注册
                    watchNode();
                }
            }
        });
        watchNode();
    }

    public String discover() {
        int size = dataList.size();
        if (size > 0) {
            int index = new Random().nextInt(size);
            return dataList.get(index);
        }
        throw new RuntimeException("没有找到对应的服务器");
    }

    private void watchNode() {
        try {
            List<String> nodeList = zooKeeper.getChildren(Constant.REGISTRY_PATH, true);
            List<String> dataList = new ArrayList<>();
            for (String node : nodeList) {
                byte[] bytes = zooKeeper.getData(Constant.REGISTRY_PATH + "/" + node, false, null);
                dataList.add(new String(bytes));
            }
            this.dataList = dataList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
