package com.github.baymin.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author zongwei
 * @date 2021-04-03 09:26:13
 */
@SpringBootApplication(scanBasePackages = {"com.baidu.fsg.uid", "com.github.baymin.tools"})
@EnableDiscoveryClient
public class ToolsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsServerApplication.class, args);
    }

    @PostConstruct
    void setDefaultTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

}
