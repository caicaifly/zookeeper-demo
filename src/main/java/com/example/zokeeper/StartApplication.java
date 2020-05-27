package com.example.zokeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: zookeeper
 * @Package: com.example.zokeeper.product
 * @Description: note
 * @Author: xiangdc16781
 * @CreateDate: 2020-01-17 10:14
 * @UpdateUser: xiangdc16781
 * @UpdateDate: 2020-01-17 10:14
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/
@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages ={"com.example.zokeeper.product"})
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class,args);

        //RestTemplate的使用参考：   https://blog.csdn.net/qq_38526573/article/details/90668738
        //通过getForEntity调用
        System.out.println("=========getForEntity======");
        String url="http://localhost:8081/product/test";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        // 获取响应的状态
        HttpStatus statusCode = entity.getStatusCode();
        // 获取响应的header信息
        HttpHeaders headers = entity.getHeaders();
        // 获取响应的body信息
        String msg = entity.getBody();
        System.out.println(statusCode);
        System.out.println(headers);
        System.out.println(msg);

        System.out.println("=========getForObject======");
        String url1="http://localhost:8081/product/test";
        RestTemplate restTemplate1 = new RestTemplate();
        // 直接返回的就是我们需要的结果，但是获取不到对应的响应状态等信息
        String msg1 = restTemplate1.getForObject(url,String.class);
        System.out.println(msg1);




    }

    public static void task(int i) throws  Exception{
        System.out.println("第"+i+"次执行...");
        if (i==5){
            System.out.println(i/0);

        }else{
            System.out.println("第"+i+"次执行完成...");
        }

    }



}
