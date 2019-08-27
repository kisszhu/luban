package com.zhl.rpc.register;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * @program codeX
 * @description:
 * @author: meilong
 * @create: 2019/08/27 08:27
 */
public class RpcRegistry {

    private String connect;

    public void createNode(String data) throws Exception {
        // 创建一个客户端程序，对于注册可以不用监听
        ZooKeeper zooKeeper = new ZooKeeper(connect, Constant.SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
            }
        });
        if (zooKeeper != null) {
            // zookeeper的节点包括三部分内容：state、data、children
            Stat stat = zooKeeper.exists(Constant.REGISTRY_PATH, false);
            if (stat == null) {
                // 如果不存在，创建一个持久的节点目录
                zooKeeper.create(Constant.REGISTRY_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            // 创建临时的序列节点，用于保存数据，在会话结束后，会自动删除
            zooKeeper.create(Constant.DATA_PATH, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        }
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }
}
