package com.admin.service.impl;

import com.admin.entity.Target;
import com.admin.entity.TaskList;
import com.admin.mapper.TargetMapper;
import com.admin.mapper.TaskMapper;
import com.admin.service.TargetService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-28 20:55
 */
@Service
public class TargetServiceImpl extends ServiceImpl<TargetMapper, Target> implements TargetService {

    @Autowired
    private TargetMapper targetMapper;

    @Autowired
    private TaskMapper taskMapper;
    /**
     * 新增目标
     * @author admin
     * @date 2024/4/28 21:21
     * @param target
     * @param userId
     * @return Long
    */
    @Override
    public Long addTarget(Target target, Long userId) {
        target.setId(userId);
        targetMapper.insert(target);
        return target.getId();
    }

    /**
     * 删除目标记录
     * @author admin
     * @date 2024/4/28 21:43
     * @param targetIds
    */
    @Override
    @Transactional
    public void delete(Long[] targetIds) {
        //1.删除目标记录
        removeBatchByIds(Arrays.asList(targetIds));
        //2.删除目标记录下的任务记录
        QueryWrapper<TaskList> wrapper = new QueryWrapper<>();
        wrapper.in("targetId", Arrays.asList(targetIds));
        taskMapper.delete(wrapper);
    }
}
