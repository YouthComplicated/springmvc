package com.lanmo.config;

import com.lanmo.dao.mongoRepository.OrderRepository;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@ComponentScan(basePackages = { "com.lanmo.entity.mongo" })
@EnableMongoRepositories(basePackageClasses = OrderRepository.class)
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

//    @Bean
//    public MongoClientFactoryBean mongo(){
//        MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();
//        factoryBean.setHost("localhost");
//        return factoryBean;
//    }
//
//    @Bean
//    public MongoOperations mongoTemplate(MongoClient mongo){
//        return new MongoTemplate(mongo,"shop");
//    }

    @Override
    public MongoClient mongoClient() {
        /**
         * 认证MongoDB服务器
         */
//        MongoCredential mongoCredential = MongoCredential.createCredential(
//                env.getProperty("mongo.username"),"shop",
//                env.getProperty("mongo.password").toCharArray()
//        );
//        return new MongoClient(new ServerAddress("localhost",271017),
//                Arrays.asList(mongoCredential));


        return new MongoClient("localhost", 27017);
    }

    @Override
    protected String getDatabaseName() {
        return "shop";
    }


}
