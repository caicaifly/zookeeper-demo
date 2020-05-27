package com.example.zokeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ForkJoinPool;

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

//        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
//        forkJoinPool.submit(()->{
//            for (int i=0;i<10;i++){
//                try {
//                    task(i);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

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
