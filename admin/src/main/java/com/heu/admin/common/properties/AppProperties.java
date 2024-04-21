package com.heu.admin.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: admin
 * @description: App配置常量
 * @author: QiuAo
 * @create: 2024-04-18 22:38
 */
@Data
@Component
@ConfigurationProperties("app")
public class AppProperties {
    /*小程序 appId*/
    private String appid;
    /*小程序 appSecret*/
    private String secret;
    /*授权类型*/
    private String grant_type;
}
