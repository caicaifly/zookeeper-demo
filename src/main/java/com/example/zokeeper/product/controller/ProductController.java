package com.example.zokeeper.product.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: zookeeper
 * @Package: com.example.zokeeper.product.controller
 * @Description: note
 * @Author: xiangdc16781
 * @CreateDate: 2020-01-17 10:07
 * @UpdateUser: xiangdc16781
 * @UpdateDate: 2020-01-17 10:07
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/

@RequestMapping("/product")
@RestController
public class ProductController {
    @RequestMapping("/get/{id}")
    public Object getProduct(HttpServletRequest request, @PathVariable("id") String id){
            int localPort = request.getLocalPort();
        System.out.println("服务调用开始....");
        return new Product(id,"productName:" + localPort);
    }

    /**
     * 测试RestTemplate的使用
     * @return
     */
    @RequestMapping("/test")
    public Object testRestTemplate(){
        System.out.println("---testRestTemplate---服务调用开始....");
        return "success";
    }




}
