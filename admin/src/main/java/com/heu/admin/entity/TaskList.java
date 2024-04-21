package com.heu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: admin
 * @description: 任务清单表
 * @author: QiuAo
 * @create: 2024-04-17 21:09
 */
@Data
@TableName("sys_user_task")
public class TaskList implements Serializable {
    /*任务清单主键*/
    @TableId
    private Long id;
    /*开始时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /*结束时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /*标题*/
    private String title;
    /*内容*/
    private String content;
    /*重要程度*/
    private String importanceValue;
    /*紧急程度*/
    private String urgencyValue;
    /*焦虑值*/
//    private String anxietyValue;
    /*目标清单主键*/
    private Long userTargetId;
    /*状态*/
    private String status;
    /*创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /*修改时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /*创建用户*/
    private Long userId;
}
