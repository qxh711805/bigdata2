package com.rimi.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

public class ZkDemo {

    public static void main(String[] args) throws Exception {
        // 创建zookeeper对象
        ZooKeeperConnection connection = new ZooKeeperConnection();
        ZooKeeper connect = connection.connect("192.168.56.101:2181");
        byte[] data;
        data = connect.getData("/a", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("数据");
                try {
                    connect.getData("/a",this,null);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, null);

        Thread.sleep(30000);
        System.out.println(new String(data));
        connect.close();
    }

    public void getAll() throws Exception{
        ZooKeeperConnection connection = new ZooKeeperConnection();
        ZooKeeper connect = connection.connect("192.168.56.101:2181");
        List<String> children = connect.getChildren("/", null);
        for (String child : children) {
//            connect.getChildren()
        }
    }
}
