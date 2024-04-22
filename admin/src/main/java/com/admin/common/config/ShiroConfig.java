package com.admin.common.config;

import com.admin.common.auth.filter.JwtFilter;
import com.admin.common.auth.realms.UserRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * @program: admin
 * @description: Shiro配置
 * @author: QiuAo
 * @create: 2024-04-18 22:07
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建shiroFilter
     * @author qiuao
     * @date 2024/4/20 11:36
     * @param securityManager
     * @return ShiroFilterFactoryBean
    */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);

        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JwtFilter());
        filterFactoryBean.setFilters(filterMap);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/**", "jwt");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }

    /**
     * 创建安全管理器
     * 把我们自定义的realm和重写的UserModularRealmAuthenticator添加到DefaultWebSecurityManager
     * @author qiuao
     * @date 2024/4/20 10:51
     * @param userRealm
     * @return DefaultWebSecurityManager
    */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //关联Realm
        manager.setRealm(userRealm);

        /*
         *  关闭shiro自带的session
         *      用了jwt的访问认证，所以要把默认session支持关掉
         *      即不保存用户登录状态，保证每次请求都重新认证
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }


    /**
     * 创建userRealm
     * @author qiuao
     * @date 2024/4/20 10:37
     * @return Realm
    */
    @Bean("userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
