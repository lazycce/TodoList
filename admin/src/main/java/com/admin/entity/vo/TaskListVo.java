package com.admin.entity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-22 21:49
 */
@Data
public class TaskListVo implements Serializable {
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
    /*状态*/
    private String status;
    /*重要程度描述*/
    private String importanceDescription;
    /*紧迫程度描述*/
    private String urgencyDescription;
    /*状态描述*/
    private String statusDescription;
}
