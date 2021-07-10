package com.github.baymin.tools.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 分布式文件系统配置项
 *
 * @author BaiZongwei
 * @date 2021/5/17 11:20
 */
@Data
@Component
@ConfigurationProperties(prefix = "dfs")
public class DFSProperties {

    /**
     * 存储方式: minio
     */
    private String store;

    /**
     * vue静态资源虚拟路径配置
     */
    private MinioProperties minio = new MinioProperties();

    @Data
    public static class MinioProperties {

        private String endpoint;

        private Integer port;

        private String accessKey;

        private String secretKey;

        private Boolean secure;

        private String bucketName;

    }
}
