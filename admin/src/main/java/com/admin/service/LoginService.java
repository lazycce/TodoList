package com.admin.service;

import com.admin.common.properties.AppProperties;
import com.admin.common.utils.CommonUtils;
import com.admin.entity.LoginUser;
import com.admin.entity.User;
import com.admin.entity.vo.LoginVo;
import com.alibaba.fastjson2.JSONObject;
import com.admin.common.auth.token.JwtToken;
import com.admin.common.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @program: admin
 * @description: 登录业务处理
 * @author: QiuAo
 * @create: 2024-04-20 14:52
 */
@Slf4j
@Service
public class LoginService {

    @Autowired
    private AppProperties appProperties;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;


    public LoginVo login(String code) {
        LoginVo loginVo = new LoginVo();
        //TODO 1.校验参数 使用异常处理

        //2.小程序登录
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appProperties.getAppid() +
                "&secret=" + appProperties.getSecret() + "&js_code=" + code + "&grant_type=" + appProperties.getGrant_type();
        String wxResStr = HttpUtils.sendGet(url);
        /*String wxResStr = "{\n" +
                "\"openid\":\"12818278172\",\n" +
                "\"session_key\":\"xxxxx\",\n" +
                "\"unionid\":\"xxxxx\",\n" +
                "\"errcode\":0,\n" +
                "\"errmsg\":\"xxxxx\"\n" +
                "}";*/
        JSONObject wxResJson = JSONObject.parse(wxResStr);
        //TODO 校验小程序登录结果
        //3.根据获取的openid获取用户，没有就默认注册此用户
        String openid = wxResJson.getString("openid");
        User user = userService.getUserByOpenid(openid);
        if (ObjectUtils.isEmpty(user)){
            user = new User();
            // 默认注册
            user.setOpenid(openid);
            user.setNickname(CommonUtils.getRadomNickName());
            userService.createUser(user);
        }
        //4.创建token，使用shiro认证
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getUserId());
        loginUser.setUniqueId(user.getOpenid());
        loginUser.setUser(user);
        String token = tokenService.createToken(loginUser);

        Subject subject = SecurityUtils.getSubject();
        JwtToken jwtToken = new JwtToken(token);
        subject.login(jwtToken);

        loginVo.setUser(user);
        loginVo.setToken(token);
        return loginVo;
    }
}
