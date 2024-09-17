package com.dongd.quesbank.utils;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.alibaba.druid.util.StringUtils;


import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix="jwt.token")
public class JwtHelper {

    private long tokenExpiration;
    private String tokenSignKey;

    public String createToken(Long userId){
        System.out.println(tokenExpiration);
        System.out.println("tokenExpiration:"+tokenExpiration);
        System.out.println("tokenSignKey:"+tokenSignKey);

        String token = Jwts.builder()

                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis()+tokenExpiration))
                .claim("userId",userId)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    public Long getUserId(String token){
        if(StringUtils.isEmpty(token))return null;
        Jws<Claims> claimsJws=Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims=claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }

    public boolean isExpiration(String token){
        try{
            boolean isExpire=Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
            return isExpire;
        }catch(Exception e){
            return true;
        }
    }
}
