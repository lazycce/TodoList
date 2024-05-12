package com.admin.service.impl;

import com.admin.entity.vo.TaskListVo;
import com.admin.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.entity.TaskList;
import com.admin.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: admin
 * @description: 任务清单业务处理
 * @author: QiuAo
 * @create: 2024-04-16 22:06
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, TaskList> implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    /*
     * 查询任务清单列表
     * @param openid
     * @return List<TaskList>
     * @author: QiuAo
     * @create: 2024/4/17-22:15
     **/
    @Override
    public List<TaskListVo> getTaskList(Long userId) {
        return taskMapper.selectTaskList(userId);
    }

    /*
     * 新增任务清单
     * @param taskList
     * @return Long 新增的主键
     * @author: QiuAo
     * @create: 2024/4/17-22:49
     **/
    @Override
    public Long addTask(TaskList taskList, Long userId) {
        taskList.setUserId(userId);
        taskMapper.insert(taskList);
        return taskList.getId();
    }

    /**
     * 更新任务
     *
     * @param taskList
     * @return Long
     * @author qiuao
     * @date 2024/4/21 17:19
     */
    @Override
    public int updateTask(TaskList taskList) {
        return taskMapper.updateById(taskList);
    }
}
