package com.avantir.yoda.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lekanomotayo on 06/02/2018.
 */

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    String[] cacheStrings = new String[]{"default"};

    //@Value("${blowfish.redis.defaultexpiration}")
    private int redisExpiration = 300;
    @Value("${spring.redis.host}")
    private String redisHost = "127.0.0.1";
    @Value("${spring.redis.port}")
    private int redisPort = 6379;

    /*
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

        // Defaults
        redisConnectionFactory.setHostName(redisHost);
        redisConnectionFactory.setPort(redisPort);
        return redisConnectionFactory;
    }
    */

    @Bean
    public RedisConnectionFactory connectionFactory() {
        /*
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel("127.0.0.1", 26379)
                .sentinel("127.0.0.1", 26380);
        return new LettuceConnectionFactory(sentinelConfig);
        */

        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }


    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = getRedisCacheManager(redisTemplate);
        cacheManager.setTransactionAware(true);
        // manually call initialize the caches as our SimpleCacheManager is not declared as a bean
        cacheManager.initializeCaches();
        return new TransactionAwareCacheManagerProxy(cacheManager);
    }



    @Bean
    public KeyGenerator keyGenerator() {

    //return new DefaultKeyGenerator();
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                // This will generate a unique key of the class name, the method name,
                // and all method parameters appended.
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }


    private RedisCacheManager getRedisCacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        List<String> cacheNameList = new ArrayList<String>();
        cacheNameList.add("default");
        cacheManager.setCacheNames(cacheNameList);
        return cacheManager;
    }


    private SimpleCacheManager getSimpleCacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ConcurrentMapCache defaultCache = new ConcurrentMapCache("default");

        List<ConcurrentMapCache> concurrentMapCacheList = new ArrayList<ConcurrentMapCache>();
        concurrentMapCacheList.add(defaultCache);

        //cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
        cacheManager.setCaches(concurrentMapCacheList);
        return cacheManager;
    }
     /*
    @Bean
    public CacheManager cacheManager() {
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        return cacheManager;
    }
    */

}
