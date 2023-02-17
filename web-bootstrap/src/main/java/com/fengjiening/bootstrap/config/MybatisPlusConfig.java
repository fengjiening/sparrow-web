package com.fengjiening.bootstrap.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhoujf
 *
 */
@Configuration
@MapperScan(value={"com.fengjiening.pub.mapper"})
public class MybatisPlusConfig {

    /**
         *  分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        // 设置sql的limit为无限制，默认是500
        return new PaginationInterceptor().setLimit(-1);
    }

    ///**
    // * mybatis-plus SQL执行效率插件【生产环境可以关闭】
    // */
    //@Bean
    //public PerformanceInterceptor performanceInterceptor() {
    //    return new PerformanceInterceptor();
    //}


}
