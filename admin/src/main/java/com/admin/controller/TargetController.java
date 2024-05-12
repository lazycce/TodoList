package com.admin.controller;

import com.admin.controller.core.BaseController;
import com.admin.entity.Target;
import com.admin.entity.core.AjaxResult;
import com.admin.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-28 20:51
 */
@RestController
@RequestMapping("/target")
public class TargetController extends BaseController {

    @Autowired
    TargetService targetService;

    @PostMapping("/save")
    public AjaxResult save(@RequestBody Target target){
        Long targetId = targetService.addTarget(target, getUserId());
        return AjaxResult.success(targetId);
    }

    @DeleteMapping("/delete/{targetIds}")
    public AjaxResult delete(@PathVariable("targetIds") Long[] targetIds){
        targetService.delete(targetIds);
        return AjaxResult.success();
    }
}
