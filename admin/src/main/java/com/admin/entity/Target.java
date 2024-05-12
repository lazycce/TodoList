package com.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: admin
 * @description: 目标清单
 * @author: QiuAo
 * @create: 2024-04-18 18:48
 */
@Data
@TableName("sys_user_target")
public class Target implements Serializable {
    /*主键*/
    @TableId
    private Long id;
    /*主题*/
    private String title;
    /*内容*/
    private String content;
    /*焦虑等级*/
    private String anxietyValue;
    /*开始时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /*结束时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /*用户id*/
    private Long userId;
    /*状态*/
    private String state;
    /*创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /*更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
