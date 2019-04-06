package com.lanmo.config;


import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;

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
     * 使用redis缓存
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        return null;
    }
    @Bean
    public EhCacheManagerFactoryBean cache(){
        EhCacheManagerFactoryBean ehCaheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCaheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCaheManagerFactoryBean;
    }




}
