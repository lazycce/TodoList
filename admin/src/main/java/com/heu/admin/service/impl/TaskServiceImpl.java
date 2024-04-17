package com.heu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heu.admin.entity.User;
import com.heu.admin.mapper.TaskMapper;
import com.heu.admin.service.TaskService;
import org.springframework.stereotype.Service;

/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-16 22:06
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, User> implements TaskService {
}
