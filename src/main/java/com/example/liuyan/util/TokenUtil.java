package com.example.liuyan.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.liuyan.entity.pojo.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/5/13 9:39
 * @packagename com.example.liuyan.util
 * @classname TokenUtil
 * @description
 */
public class TokenUtil {
    private static String privateKey = "2122121";

    /**
     * 加密token.
     */
    public static String getToken(User user) {
        //这个是放到负载payLoad 里面,魔法值可以使用常量类进行封装.
        String token = JWT
                .create()
                .withClaim("userId" ,user.getId())
                .withClaim("userName", user.getUsername())
                .withClaim("email",user.getEmail())
                .withClaim("timeStamp", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(privateKey));
        return token;
    }


    public static Map<String,Object> parseToken(String token) {
        HashMap<String, Object> user = new HashMap<>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(privateKey))
                .build().verify(token);
        Claim userId = decodedjwt.getClaim("userId");
        Claim userName = decodedjwt.getClaim("userName");
        Claim email = decodedjwt.getClaim("email");
        Claim timeStamp = decodedjwt.getClaim("timeStamp");
        user.put("userId",userId.asInt());
        user.put("userName",userName.asString());
        user.put("email",email.asString());
        user.put("timeStamp",timeStamp.asString());
        return user;
    }
}
