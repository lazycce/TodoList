package com.heu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-16 21:15
 */
@Data
public class User {
    /*用户id*/
    @TableId
    private Long userNo;
    /*昵称*/
    private String nickname;
    /*微信openid*/
    private String openid;
}
