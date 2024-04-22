package com.admin.service;

import com.admin.entity.vo.TaskListVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.admin.entity.TaskList;

import java.util.List;

/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-16 21:33
 */
public interface TaskService extends IService<TaskList> {

    List<TaskListVo> getTaskList(Long userId);

    Long addTask(TaskList taskList, Long userId);

    int updateTask(TaskList taskList);


}
