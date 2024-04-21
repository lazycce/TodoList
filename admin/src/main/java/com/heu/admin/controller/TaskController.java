package com.heu.admin.controller;

import com.heu.admin.controller.core.BaseController;
import com.heu.admin.entity.TaskList;
import com.heu.admin.entity.core.AjaxResult;
import com.heu.admin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @program: admin
 * @description: 任务清单接口
 * @author: QiuAo
 * @create: 2024-04-16 21:12
 */
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    TaskService taskService;

    /*
     * 新增或修改任务
     * @param taskList
     * @return AjaxResult
     * @author: QiuAo
     * @create: 2024/4/17-22:59
     **/
    @PostMapping("/save")
    public AjaxResult save(@RequestBody TaskList taskList){
        Long taskId = taskService.addTask(taskList, getUserId());
        return AjaxResult.success(taskId);
    }

    /*
     * 根据用户主键查询任务清单列表
     * @param userNo
     * @return AjaxResult
     * @author: QiuAo
     * @create: 2024/4/17-22:12
     **/
    @GetMapping("/list")
    public AjaxResult list(){
        List<TaskList> taskList = taskService.getTaskList(getUserId());
        return AjaxResult.success(taskList);
    }

    /**
     * 批量删除任务
     * @author qiuao
     * @date 2024/4/21 14:22
     * @param taskIds
     * @return AjaxResult
    */
    @DeleteMapping("/delete/{taskIds}")
    public AjaxResult delete(@PathVariable Long[] taskIds){
        taskService.removeBatchByIds(Arrays.asList(taskIds));
        return AjaxResult.success();
    }

    /**
     * 编辑任务
     * @author qiuao
     * @date 2024/4/21 17:30
     * @param taskList
     * @return AjaxResult
    */
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody TaskList taskList){
        return AjaxResult.success(taskService.updateTask(taskList));
    }
}
