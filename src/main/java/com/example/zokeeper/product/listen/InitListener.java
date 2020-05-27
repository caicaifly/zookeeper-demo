package com.example.zokeeper.product.listen;

import com.example.zokeeper.product.ServiceRegister;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.InetAddress;
import java.util.Properties;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: zookeeper
 * @Package: com.example.zokeeper.product.listen
 * @Description: note
 * @Author: xiangdc16781
 * @CreateDate: 2020-01-17 10:05
 * @UpdateUser: xiangdc16781
 * @UpdateDate: 2020-01-17 10:05
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/
@WebListener
public class InitListener  implements ServletContextListener {
    @Override
    //容器初始化的时候会调用
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("容器初始化...");
        Properties properties = new Properties();
        try {
            properties.load(InitListener.class.getClassLoader().getResourceAsStream("application.properties"));
            //获得IP
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            //获得端口
            int port = Integer.valueOf(properties.getProperty("server.port"));
            ServiceRegister.register(hostAddress,port);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
