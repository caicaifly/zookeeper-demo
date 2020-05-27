package com.example.zokeeper.product.controller;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: zookeeper
 * @Package: com.example.zokeeper.product.controller
 * @Description: note
 * @Author: xiangdc16781
 * @CreateDate: 2020-01-17 10:10
 * @UpdateUser: xiangdc16781
 * @UpdateDate: 2020-01-17 10:10
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/
public class Product {
    public String id;
    public String productName;


    Product(String id,String productName ){
     this.id=id;
     this.productName=productName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
