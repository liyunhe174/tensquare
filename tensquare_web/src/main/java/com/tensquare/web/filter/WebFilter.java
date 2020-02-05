package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/31 22:31
 * @Description:
 */
@Component
public class WebFilter extends ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }
        String url = request.getRequestURL().toString();
        if (url.indexOf("login") > 0) {
            System.out.println("登录页面" + url);
            return null;
        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if ("user".equals(claims.get("roles"))) {
                    requestContext.addZuulRequestHeader("Authorization", header);
                    return null;
                }
            }
        }
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(401);
        requestContext.setResponseBody("权限不足");
        requestContext.getResponse().setContentType("text/html;charset=UTF-8");

        return null;
    }
}
