package com.heu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heu.admin.entity.User;

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
