package com.admin.common.auth.realms;

import com.admin.common.utils.StringUtils;
import com.admin.entity.LoginUser;
import com.admin.common.auth.token.JwtToken;
import com.admin.service.TokenService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: admin
 * @description: UserRealm
 * @author: QiuAo
 * @create: 2024-04-20 10:43
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private TokenService tokenService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权=>doGetAuthorizationInfo");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String)  authenticationToken.getPrincipal();
        if (StringUtils.isEmpty(token)){
            throw new AuthenticationException("不含token");
        }
        LoginUser loginUser = tokenService.getLoginUser(token);
        tokenService.refreshToken(loginUser);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginUser, token, getName());
        System.out.println("执行认证=>doGetAuthenticationInfo");
        return info;
    }
}
