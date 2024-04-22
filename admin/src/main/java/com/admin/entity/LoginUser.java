package com.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @program: admin
 * @description: 用户信息实体类，存于session之中
 * @author: QiuAo
 * @create: 2024-04-20 15:37
 */
@Data
public class LoginUser implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户唯一标识
     */
    private String uniqueId;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private User user;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
