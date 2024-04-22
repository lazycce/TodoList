package com.heu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heu.admin.entity.TaskList;
import com.heu.admin.entity.vo.TaskListVo;

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
