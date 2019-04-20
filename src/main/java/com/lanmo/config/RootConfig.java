package com.lanmo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mongodb.ClientSessionOptions;
import com.mongodb.DB;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;


/**
 * Spring的容器不扫描controller
 *
 * 该类注入spring 管理相关bean(数据层和逻辑层) 除了Controller其他都能注入
 */
@ComponentScan(value = "com.lanmo" , excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
})
@EnableTransactionManagement //开启注解
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


    /**
     * 声明hibernate的session工厂
     * @return
     */
//    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataBasicSource());
        localSessionFactoryBean.setPackagesToScan("com.lanmo.entity");
//        localSessionFactoryBean.setAnnotatedClasses(User.class);
        Properties props = new Properties();
        props.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        localSessionFactoryBean.setHibernateProperties(props);
        return localSessionFactoryBean;
    }

    /**
     * 当使用Hibernate上下文的Session时，抛出的就不是Spring的异常，
     * 而是HibernateException，如果我们还想看到Spring的异常体系
     * 在DAO实现类上加@Respository注解，并且注册一个PersistenceExceptionTranslationPostProcessor实例即可。
     * @return
     */
//    @Bean
    public BeanPostProcessor persistenceTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * spring 接管的事务
     * @return
     * @throws PropertyVetoException
     */
    @Bean(name="transactionManager")
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {

        /**
         * spring 的 PlatformTransactionManager
         */
//        PlatformTransactionManager  dataSourceTransactionManager = new DataSourceTransactionManager(dataBasicSource());
//        return dataSourceTransactionManager;

        /**
         * jpa transactionManager
         */
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory(jpaVendorAdapter()).getObject());
        transactionManager.setDataSource(dataBasicSource());
        return transactionManager;
    }

    /**
     *  容器管理类型jpa
     *  not success :No persistence units parsed from {classpath*:META-INF/persistence.xml}
     * @return
     */
    /**
     * spring 提供多个JPA厂商适配器
     * @param jpaVendorAdapter
     * @return
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setDataSource(dataBasicSource());
        entityManagerFactoryBean.setPackagesToScan("com.lanmo.entity");
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return adapter;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor(){
        return new PersistenceAnnotationBeanPostProcessor();
    }


    /**
     * 直接使用该方式返回JpaTransactionManager  not work
     * @return
     */
//    @Bean(name="transactionManager")
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory(jpaVendorAdapter()).getObject());
        transactionManager.setDataSource(dataBasicSource());
        return transactionManager;
    }


    /**
     * 应用程序管理类型JPA 不采取这种方式
     * 参考:spring in action page 319
     * @return
     */
//    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalEntityManagerFactoryBean();
        localEntityManagerFactoryBean.setPersistenceUnitName("user");
        return localEntityManagerFactoryBean;
    }

//    @Bean
    public RedisConnectionFactory redisCF(){
        //默认的本机和端口
        JedisConnectionFactory cf = new JedisConnectionFactory();
        return new JedisConnectionFactory();
    }





}
