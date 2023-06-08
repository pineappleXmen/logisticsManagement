package com.pineapple.pineapplelogistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 0:25 2023/4/25
 * @Modifier by:
 */
@Configuration
@ComponentScan("com.pineapple")
@EnableTransactionManagement
@EnableWebMvc
public class SpringConfig {
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager dtm = new DataSourceTransactionManager();
        dtm.setDataSource(dataSource);
        return dtm;
    }
}
