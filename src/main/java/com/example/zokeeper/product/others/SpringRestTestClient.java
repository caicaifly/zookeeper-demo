package com.example.zokeeper.product.others;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: zookeeper
 * @Package: com.example.zokeeper.product.others
 * @Description: 请求服务
 * @Author: xiangdc16781
 * @CreateDate: 2020-05-27 13:45
 * @UpdateUser: xiangdc16781
 * @UpdateDate: 2020-05-27 13:45
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/

public class SpringRestTestClient {
    public static final String REST_SERVICE_URI = "http://localhost:8082/sc";

    private static void listAllUsers(){
        RestTemplate restTemplate = new RestTemplate();
        String param = "es1";
        try {
            String result = restTemplate.postForObject(REST_SERVICE_URI+"/es", param, String.class);
            System.out.println("es-result~" + result);
        } catch (RestClientException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        listAllUsers();
    }
}