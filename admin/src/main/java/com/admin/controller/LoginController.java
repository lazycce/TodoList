package com.admin.controller;

import com.admin.controller.core.BaseController;
import com.admin.entity.core.AjaxResult;
import com.admin.entity.vo.LoginVo;
import com.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: admin
 * @description: 登录接口
 * @author: QiuAo
 * @create: 2024-04-18 19:09
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    /*
     * 登录
     * @param loginDto
     * @return AjaxResult
     * @author: QiuAo
     * @create: 2024/4/18-22:22
     **/
    @PostMapping("/login")
    public AjaxResult login(@RequestParam(required = true) String code){
        LoginVo loginVo = loginService.login(code);
        return AjaxResult.success(loginVo);
    }
}
