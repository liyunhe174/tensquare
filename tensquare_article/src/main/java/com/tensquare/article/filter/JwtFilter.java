package com.tensquare.article.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 14:45
 * @Description:  token拦截器
 */
@Component
public class JwtFilter implements HandlerInterceptor {


    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Claims claims = jwtUtil.parseJWT(token);

            if (claims != null) {
                if (claims.get("roles").equals("admin")) {
                    request.setAttribute("admin_claims", claims);
                }
                if (claims.get("roles").equals("user")) {

                    request.setAttribute("user_claims", claims);
                }
            }
        }

        return true;
    }
}
