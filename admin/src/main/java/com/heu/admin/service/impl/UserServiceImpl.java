package com.heu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heu.admin.entity.User;
import com.heu.admin.mapper.UserMapper;
import com.heu.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: admin
 * @description: user业务处理
 * @author: QiuAo
 * @create: 2024-04-17 21:23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByOpenid(String openid) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("openid", openid));
    }

    @Override
    public Integer createUser(User user) {
        return userMapper.insert(user);
    }
}
