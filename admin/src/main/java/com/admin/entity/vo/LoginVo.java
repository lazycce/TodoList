package com.admin.entity.vo;

import com.admin.entity.User;
import lombok.Data;

/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-20 14:20
 */
@Data
public class LoginVo {
    private String token;
    private User user;
}
