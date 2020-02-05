package com.tenquare.jwt;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/28 17:09
 * @Description:
 */
public class CreateJwt {

    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("6666")
                .setIssuedAt(new Date())
                .setSubject("小明")
                .setExpiration(new Date(new Date().getTime() + 60000))
                .claim("role", "admin")
                .signWith(SignatureAlgorithm.HS256 ,"tensquare")
                ;

        System.out.println(jwtBuilder.compact());

    }


}
