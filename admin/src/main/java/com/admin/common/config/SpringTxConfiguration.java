package com.admin.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: admin
 * @description: 事务管理
 * @author: QiuAo
 * @create: 2024-04-28 22:29
 */
@Configuration
@EnableTransactionManagement
public class SpringTxConfiguration {

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(DruidDataSourceBuilder.create().build());
        return manager;
    }

}
