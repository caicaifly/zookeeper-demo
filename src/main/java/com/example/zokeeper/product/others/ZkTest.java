package com.example.zokeeper.product.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.strategies.RandomStrategy;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: zookeeper
 * @Package: com.example.zokeeper.product.others
 * @Description: 服务注册于发现
 * @Author: xiangdc16781
 * @CreateDate: 2020-05-27 13:41
 * @UpdateUser: xiangdc16781
 * @UpdateDate: 2020-05-27 13:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/
public class ZkTest {
    private static String basePath = "/es_path";
    private static ServiceDiscovery<Void> serviceDiscovery;
    private static ServiceProvider<Void> provider;

    public static void main(String[] args) throws IOException {
        httpserverService();
    }


    public static CuratorFramework getClient() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 1));
        curatorFramework.start();
        return curatorFramework;
    }

    /**
     * 服务发现
     * @param client
     * @return
     * @throws Exception
     */
    public static ServiceDiscovery<Void> getServiceDiscovery(CuratorFramework client) throws Exception {
        JsonInstanceSerializer<Void> serializer = new JsonInstanceSerializer<Void>(
                Void.class);
        ServiceDiscovery<Void> serviceDiscovery = ServiceDiscoveryBuilder
                .builder(Void.class)
                .client(client)
                .serializer(serializer)
                .basePath(basePath)
                .build();
        serviceDiscovery.start();
        return serviceDiscovery;
    }

    /**
     * 注册一个实例：
     * @return
     * @throws Exception
     */
    public static ServiceInstance<Void> getInstance1() throws Exception {
        ServiceInstance<Void> instance1 = ServiceInstance.<Void>builder()
                .name("es1")
                .port(8082)
                .address("127.0.0.1")
                .uriSpec(new UriSpec("http://127.0.0.1:8082"))
                .build();
        return instance1;
    }

    public static ServiceProvider<Void> serviceProvider(ServiceDiscovery<Void> serviceDiscovery,
                                                        String serviceName) throws Exception {
        ServiceProvider<Void> provider = serviceDiscovery.serviceProviderBuilder().
                serviceName(serviceName).
                providerStrategy(new RandomStrategy<Void>())
                .build();
        provider.start();
        return provider;
    }


    public static void process(String param) throws Exception {
        if (null == serviceDiscovery) {
            LoggerFactory.getLogger("");
            serviceDiscovery = getServiceDiscovery(getClient());
            //注册服务
            serviceDiscovery.registerService(getInstance1());
        }

        if (null == provider) {
            provider = serviceProvider(serviceDiscovery, param);
        }
        if (null != provider) {
            System.out.println("address~~~" + provider.getInstance().getAddress() +
                    ", port~~~" + provider.getInstance().getPort());
        }
    }

    //启动服务，监听来自客户端的请求
    public static void httpserverService() throws IOException {
        HttpServerProvider provider = HttpServerProvider.provider();
        //监听端口8082,能同时接 受100个请求
        HttpServer httpserver =provider.createHttpServer(new InetSocketAddress(8082), 100);
        httpserver.createContext("/sc/es", new MyHttpHandler());
        httpserver.setExecutor(Executors.newFixedThreadPool(5));
        httpserver.start();
        System.out.println("server started");
    }
    //Http请求处理类
    static class MyHttpHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {
            String responseMsg = "ok";   //响应信息

            InputStream in = httpExchange.getRequestBody(); //获得输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String temp = null;
            while((temp = reader.readLine()) != null) {
                System.out.println("client request:"+temp);
                try {
                    process(temp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            httpExchange.sendResponseHeaders(200, responseMsg.length()); //设置响应头属性及响应信息的长度
            OutputStream out = httpExchange.getResponseBody();  //获得输出流
            out.write(responseMsg.getBytes());
            out.flush();
            httpExchange.close();
        }
    }

}
