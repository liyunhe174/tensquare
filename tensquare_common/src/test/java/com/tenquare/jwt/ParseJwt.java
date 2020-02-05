package com.tenquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/28 17:40
 * @Description:
 */
public class ParseJwt {

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjY2IiwiaWF0IjoxNTgwMjA1MDA1LCJzdWIiOiLlsI_mmI4iLCJleHAiOjE1ODAyMDUwNjUsInJvbGUiOiJhZG1pbiJ9.Yne8cQDMdRsuGowYMn-Efu4EN0N_2lSZQMm_DqAOHqE";
        Claims c = Jwts.parser().setSigningKey("tensquare").parseClaimsJws(token).getBody();
        System.out.println("用户id" + c.getId());
        System.out.println("用户名称" + c.getSubject());
        System.out.println("用户创建时间" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getIssuedAt()));
        System.out.println("用户角色：" +c.get("role"));
    }
}
