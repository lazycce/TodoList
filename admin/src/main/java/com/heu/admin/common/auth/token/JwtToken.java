package com.heu.admin.common.auth.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @program: admin
 * @description: 自定义JwtToken
 * @author: QiuAo
 * @create: 2024-04-20 21:16
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = 1L;
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
