package com.etoak.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    public static final String KEY_String = "11111111111111111111111111111111";

    /**
     * 签名 KEY (hmacSha byte => 至少 256bit)
     */
    public static final Key KEY = Keys.hmacShaKeyFor(KEY_String.getBytes());


    public static final long EXPIRE = 1000 * 60 * 60 * 12;

    public static String create(Map<String, Object> claims) {
        // 签发时间
        Date issueDate = new Date();
        // 过期时间 = 签发时间+过期的毫秒数
        Date expireDate = new Date(issueDate.getTime() + EXPIRE);

        return Jwts.builder()
                .signWith(KEY)
                .setClaims(claims)
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .compact();
    }

    public static Map<String, Object> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey( KEY)
                .build()
                .parseClaimsJws( token)
                .getBody();
    }
}
