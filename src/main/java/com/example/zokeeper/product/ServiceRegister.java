package com.example.zokeeper.product;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.net.InetAddress;
import java.util.List;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: zookeeper
 * @Package: com.example.zokeeper.product
 * @Description: note
 * @Author: xiangdc16781
 * @CreateDate: 2020-01-17 9:58
 * @UpdateUser: xiangdc16781
 * @UpdateDate: 2020-01-17 9:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/
public class ServiceRegister {
      static final String BASE_SERVICE = "/service";
      static final String SERVICE_NAME = "/product";
    /**
     * 在zk创建根节点path,在根节点下创建临时子节点用于存放服务ip和端口
     */
    public static void register(String address,int port) {
        /**
         * 在zk创建根节点path,在根节点下创建临时子节点用于存放服务ip和端口
         */
        System.out.println("开始服务注册");
        try {
            String path = BASE_SERVICE + SERVICE_NAME;
            ZkClient zkClient=new ZkClient("localhost:2181");
            ZooKeeper zooKeeper = new ZooKeeper("localhost:2181",9000,(watchedEvent) -> {});
            System.out.println("zk的全部节点信息：");
            List<String> list = zooKeeper.getChildren("/", false);
            for (String item : list) {
                System.out.println(item);
            }
            Thread.sleep(2000);
            Stat exists = zooKeeper.exists(BASE_SERVICE + SERVICE_NAME, false);
            //先判断服务根路径是否存在
            if (exists == null){
                zooKeeper.create(BASE_SERVICE ,"service".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            //服务注册
            InetAddress inetAddress=InetAddress.getLocalHost();
            String ip=inetAddress.getHostAddress().toString();//获得本机Ip
            System.out.println(zkClient);
            zkClient.createEphemeral(BASE_SERVICE+"/"+ip);
            System.out.println("product服务注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
