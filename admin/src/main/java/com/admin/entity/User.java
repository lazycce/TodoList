package com.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: admin
 * @description: 用户表
 * @author: QiuAo
 * @create: 2024-04-16 21:15
 */
@Data
@TableName("sys_user")
public class User implements Serializable {
    /*用户id*/
    @TableId
    private Long userId;
    /*昵称*/
    private String nickname;
    /*用户状态*/
    private String status;
    /*用户头像*/
    private String avatar;
    /*微信openid*/
    private String openid;
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;

}
