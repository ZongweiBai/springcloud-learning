package com.github.baymin.tools.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zongwei
 */
@Configuration
@MapperScan(basePackages = "com.baidu.fsg.uid.worker.dao")
public class MybatisPlusConfig {



}
