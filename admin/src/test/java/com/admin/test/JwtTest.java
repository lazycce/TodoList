package com.admin.test;

import com.admin.entity.LoginUser;
import com.admin.service.TokenService;
import io.jsonwebtoken.Claims;
import org.ehcache.Cache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @program: admin
 * @description:
 * @author: QiuAo
 * @create: 2024-04-20 16:31
 */
@SpringBootTest
public class JwtTest {
    @Autowired
    TokenService tokenService;

   /* @Autowired
    CacheManager cacheManager;*/
    @Autowired
    Cache<String, LoginUser> ehCache;

    @Test
    public void jwtTest() {
       /* SecretKey secretKey = Jwts.SIG.HS512.key().build();

        HashMap<String, String> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, "12818278172");
        String token = Jwts.builder()
                .claims(claims)
                .signWith(secretKey)
                .compact();


        System.out.println("token=>" + token);

        Jws<Claims> claimsJws = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
        Claims claims1 = claimsJws.getPayload();

        System.out.println(claims1);*/

        LoginUser loginUser = new LoginUser();
        loginUser.setUniqueId("12818278172");
        String token = tokenService.createToken(loginUser);
        Claims claims = tokenService.parseToken(token);
        System.out.println(claims);


    }

    @Test
    public void ehcacheTest(){
        //Cache<String, LoginUser> cache = cacheManager.getCache(Constants.TOKEN, String.class, LoginUser.class);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(11111111L);
        loginUser.setUniqueId("1121212");
        ehCache.put("1", loginUser);

        LoginUser loginUser1 = ehCache.get("1");
        System.out.println("************************=>" + loginUser1);
    }
}
