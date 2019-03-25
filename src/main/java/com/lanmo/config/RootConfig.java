package com.lanmo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


/**
 * Spring的容器不扫描controller
 *
 * 该类注入spring 管理相关bean(数据层和逻辑层) 除了Controller其他都能注入
 */
@ComponentScan(value = "com.lanmo" , excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
})
public class RootConfig {

    /**
     * JNDI 数据源
     */
//    @Bean
    public JndiObjectFactoryBean dataSource(){
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/lan");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        return jndiObjectFactoryBean;
    }

    /**
     * dbcp 数据源
     * @return
     */
    @Bean
    public BasicDataSource dataBasicSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("123456");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/dev?serverTimezone=UTC&characterEncoding=utf-8  ");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setMaxActive(10);
        basicDataSource.setInitialSize(5);
        return basicDataSource;
    }


    /**
     * c3p0数据源
     * @return
     * @throws PropertyVetoException
     */
//    @Bean
    public DataSource dataSourceC3p0() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("123456");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dev");
        comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
        return comboPooledDataSource;
    }

    /**
     * spring 提供的模板
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        //spring对@Configuration会特殊处理，多次调用会从容器找组件
        return new JdbcTemplate(dataBasicSource());
    }

    /**
     * 命名参数
     * @return
     */
    @Bean
    public NamedParameterJdbcTemplate nameJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataBasicSource());
    }

}
