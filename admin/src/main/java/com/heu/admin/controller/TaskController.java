package com.heu.admin.controller;

import com.heu.admin.entity.User;
import com.heu.admin.entity.core.AjaxResult;
import com.heu.admin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-16 21:12
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    /*
     * 测试888888888888
     * @param userEntity
     * @return AjaxResult
     * @author: QiuAo
     * @create: 2024/4/16-23:03
     **/
    @PostMapping ("/save")
    public AjaxResult saveUser(@RequestBody User userEntity){
        taskService.save(userEntity);
        return AjaxResult.success();
    }
}
