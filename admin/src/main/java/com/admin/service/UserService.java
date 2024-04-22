package com.admin.service;

import com.admin.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @program: admin
 * @description: user业务处理接口
 * @author: QiuAo
 * @create: 2024-04-17 21:21
 */
public interface UserService extends IService<User> {
    User getUserByOpenid(String openid);

    Integer createUser(User user);
}
