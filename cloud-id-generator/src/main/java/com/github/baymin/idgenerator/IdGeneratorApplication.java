package com.github.baymin.idgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zongwei
 * @date 2021-04-03 09:26:13
 */
@SpringBootApplication(scanBasePackages = {"com.baidu.fsg.uid", "com.github.baymin.idgenerator"})
@EnableDiscoveryClient
public class IdGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdGeneratorApplication.class, args);
    }

}
