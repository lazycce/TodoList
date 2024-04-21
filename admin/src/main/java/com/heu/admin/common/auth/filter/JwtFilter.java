package com.heu.admin.common.auth.filter;

import com.heu.admin.common.auth.token.JwtToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: admin
 * @description: 自定义过滤器
 * @author: QiuAo
 * @create: 2024-04-21 20:08
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("JWTFilter启动");

        //指定请求不经过该过滤器
        if(((HttpServletRequest) request).getRequestURI().endsWith("login")){
            return true;
        }
        // 判断请求的请求头是否带token属性，也就是判断用户是否一定登录
        if(isLoginAttempt(request, response)){
            // 如果请求头中包含token，则执行executeLogin方法进行登入操作，检查token是否正确
            System.out.println("用户已经登录");
            try {
                return executeLogin(request, response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            System.out.println("用户没有登录");
        }
        return true;
    }


    /**
     * 判断用户是否想要登录
     *    在请求头中检查是否有token就行
     * @author qiuao
     * @date 2024/4/21 21:18
     * @param request
     * @param response
     * @return boolean
    */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        System.out.println("判断用户是否想要登入");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        return null != httpServletRequest.getHeader("token");
    }

    /**
     * 执行登录
     * @author qiuao
     * @date 2024/4/21 21:18
     * @param request
     * @param response
     * @return boolean
    */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("执行登录");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        try{
            getSubject(request, response).login(new JwtToken(token));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 跨域支持
     * @author qiuao
     * @date 2024/4/21 21:19
     * @param request
     * @param response
     * @return boolean
    */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("跨域支持");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个 option请求，这里我们给 option请求直接返回正常状态
        if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
