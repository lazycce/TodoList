package com.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @program: admin
 * @description: 选项值表
 * @author: QiuAo
 * @create: 2024-04-17 21:16
 */
@Data
@TableName("sys_user_option")
public class Option {
    /*主键*/
    @TableId
    private Long optionNo;
    /*选项值*/
    private String optionValue;
    /*选项值描述*/
    private String optionDescription;
}
