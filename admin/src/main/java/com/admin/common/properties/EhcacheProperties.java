package com.admin.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: admin
 * @description: Ehcache配置常量
 * @author: QiuAo
 * @create: 2024-04-20 22:40
 */
@Data
@Component
@ConfigurationProperties("spring.cache.ehcache")
public class EhcacheProperties {
    /**
     * ehcache heap大小
     * jvm内存中缓存的key数量
     */
    private int heap;
    /**
     * ehcache offheap大小
     * 堆外内存大小, 单位: MB
     */
    private int offheap;
    /**
     * 磁盘持久化目录
     */
    private String diskDir;
    /**
     * ehcache disk
     * 持久化到磁盘的大小, 单位: MB
     * diskDir有效时才生效
     */
    private int disk;
    /*过期时间*/
    private Integer expireTime;
}
