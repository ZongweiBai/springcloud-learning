package com.github.baymin.tools.config;

import com.github.baymin.tools.repository.DfsRepository;
import com.github.baymin.tools.repository.impl.MinIORepository;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

@ConditionalOnExpression("'${dfs.store}'.equals('minio')")
@Configuration
public class MinioConfig {

    @Autowired
    private DFSProperties dfsProperties;

    @Bean
    public MinioClient minioClient() {
        DFSProperties.MinioProperties minioProperties = dfsProperties.getMinio();
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint(), minioProperties.getPort(), minioProperties.getSecure())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

    @Bean
    public IdGenerator idGenerator() {
        return new AlternativeJdkIdGenerator();
    }

    @Bean("minioDFSRepository")
    public DfsRepository dfsRepository() {
        return new MinIORepository();
    }

}
