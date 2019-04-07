package com.lanmo.config;


import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@EnableCaching
public class CachingConfig {

//    @Bean
//    public CacheManager cacheManager(CacheManager cacheManager){
//        return new ConcurrentMapCacheManager();
//    }

//    @Bean
//    public EhCacheCacheManager cacheManager(CacheManager cacheManager){
//        return new EhCacheCacheManager(cacheManager);
//    }
//    @Bean
//    public EhCacheManagerFactoryBean cache(){
//        EhCacheManagerFactoryBean ehCaheManagerFactoryBean = new EhCacheManagerFactoryBean();
//        ehCaheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
//        return ehCaheManagerFactoryBean;
//    }


    /**
     * 使用redis缓存管理器bean
     * @return
     */
    @Bean
    public org.springframework.cache.CacheManager cacheManager(RedisTemplate redisTemplate){
        /**
         * 没有该构造器??? 版本的问题?
         */
//        return new RedisCacheManager(redisTemplate);
        return null;
    }

    /**
     * redis 连接工厂bean
     * @return
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    /**
     * redisTemplate bean
     * @param redisCF
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisCF){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisCF);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 配置多个缓存管理器
     *
     * todo redis  如何创建redisManager未解决
     * @param cm
     * @return
     */
    @Bean
    public org.springframework.cache.CacheManager cacheManager(net.sf.ehcache.CacheManager cm){
        CompositeCacheManager cacheManager = new CompositeCacheManager();
        List<org.springframework.cache.CacheManager> managers = new ArrayList<>();
        managers.add(new EhCacheCacheManager(cm));
//        managers.add(new RedisCacheManager(redisTemplate()));
        return cacheManager;
    }




}
