package com.yemage.config;

import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yemage
 */
@Configuration
@MapperScan("com.yemage.dao")
public class MybatisPlusConfig {

    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }
}
