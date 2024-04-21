package com.heu.admin.common.config;

import com.heu.admin.common.constant.Constants;
import com.heu.admin.entity.LoginUser;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @program: admin
 * @description: Ehcache缓存配置
 * @author: QiuAo
 * @create: 2024-04-20 22:07
 */
@Configuration
@EnableCaching
public class EhcacheConfig {
    @Value("${spring.cache.ehcache.heap}")
    private int heap;

    @Value("${spring.cache.ehcache.offheap}")
    private int offheap;

    @Value("${spring.cache.ehcache.disk}")
    private int disk;

    @Value("${spring.cache.ehcache.diskDir}")
    private String diskDir;

    @Value("${spring.cache.ehcache.expireTime}")
    private Integer expireTime;

    @Bean("ehcacheManager")
    public CacheManager ehcacheCacheManager(){

        ResourcePools resourcePools = ResourcePoolsBuilder
                .newResourcePoolsBuilder()
                // 堆内缓存大小
                .heap(heap, EntryUnit.ENTRIES)
                // 堆外缓存大小
                .offheap(offheap, MemoryUnit.MB)
                // 文件缓存大小
                .disk(disk, MemoryUnit.MB)
                .build();
        //生成配置
        //配置存储键值格式及过期策略
        //如:withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofHours(24)) 最后一次使用后计算，所存储的时间超过24小时
        //如：withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofHours(24))) 从缓存开始计算，所存储的时间超过24小时
        //如：withExpiry(ExpiryPolicyBuilder.noExpiration()) 永不过期
        CacheConfiguration<String, LoginUser> configuration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, LoginUser.class, resourcePools)
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(expireTime)))
                .build();

        CacheManager cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(diskDir))
                .withCache(Constants.TOKEN, configuration)
                .build(true);
        return cacheManager;
    }

    @Bean("enCache")
    public Cache<String, LoginUser> getEhcache(@Qualifier("ehcacheManager") CacheManager cacheManager){
        return cacheManager.getCache(Constants.TOKEN, String.class, LoginUser.class);
    }

}
