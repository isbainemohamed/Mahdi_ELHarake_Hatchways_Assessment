package com.example.hatchway_assessment_mahdielharake.Configuration;

import com.example.hatchway_assessment_mahdielharake.util.ConstMapper;

import org.springframework.cache.CacheManager;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;


import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@org.springframework.context.annotation.Configuration
public class Config implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(25);
        executor.setQueueCapacity(100);
        executor.initialize();
        return executor;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).recordStats();
    }

    public CacheManager cacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager(ConstMapper.CACHE_NAME);
        caffeineCacheManager.setCaffeine(caffeineConfig());
        return caffeineCacheManager;
    }


}