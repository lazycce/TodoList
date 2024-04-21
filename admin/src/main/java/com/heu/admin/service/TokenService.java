package com.heu.admin.service;

import com.heu.admin.common.constant.Constants;
import com.heu.admin.common.properties.EhcacheProperties;
import com.heu.admin.entity.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: admin
 * @description: token业务处理
 * @author: QiuAo
 * @create: 2024-04-20 15:10
 */
@Component
public class TokenService {

    private volatile static SecretKey key;

    @Autowired
    private Cache<String, LoginUser> ehcache;
    @Autowired
    private EhcacheProperties ehcacheProperties;

    /**
     * 双重检验锁获取安全秘钥
     * @return SecretKey
     * @author qiuao
     * @date 2024/4/20 17:19
     */
    private static SecretKey getUniqueSecretKey() {
        //判断对象是否已经存在，没有则实例化进入加锁代码
        if (key == null) {
            //类对象加锁
            synchronized (SecretKey.class) {
                if (key == null) {
                    key = Jwts.SIG.HS512.key().build();
                }
            }
        }
        return key;
    }

    /**
     * 使用JJWT创建token
     *
     * @param loginUser
     * @return String
     * @author qiuao
     * @date 2024/4/20 21:55
     */
    public String createToken(LoginUser loginUser) {
        refreshToken(loginUser);
        Map<String, String> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, loginUser.getUniqueId());
        return createToken(claims);
    }

    private String createToken(Map<String, String> claims) {

        return Jwts.builder()
                .claims(claims)
                .signWith(getUniqueSecretKey())
                .compact();
    }

    public LoginUser getLoginUser(String token) {
        Claims claims = parseToken(token);
        String uniqueId = claims.get(Constants.LOGIN_USER_KEY, String.class);
        return ehcache.get(getTokenKey(uniqueId));
    }


    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + ehcacheProperties.getExpireTime() * Constants.MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getUniqueId());
        ehcache.put(userKey, loginUser);
    }

    /**
     * 从令牌中获取数据信息
     *
     * @param token
     * @return Claims
     * @author qiuao
     * @date 2024/4/20 21:42
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getUniqueSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String getTokenKey(String uuid) {
        return Constants.LOGIN_USER_KEY + uuid;
    }
}
