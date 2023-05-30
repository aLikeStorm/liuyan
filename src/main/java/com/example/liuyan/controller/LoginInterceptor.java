package com.example.liuyan.controller;

import com.example.liuyan.entity.dto.UserDTO;
import com.example.liuyan.entity.pojo.User;
import com.example.liuyan.util.TokenUtil;
import com.example.liuyan.util.UserHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 17:26
 * @packagename com.example.liuyan.controller
 * @classname LoginInterceptor
 * @description
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo 判断是否需要拦截,如果刷新拦截器中没有取到User，那么就不然这些通过
        String token = request.getHeader("authorization");
        if ( token == null || "".equals(token.trim())) {
//            response.setStatus(401);
//            return false;
            HashMap<String, Object> user = new HashMap<>();
            user.put("userId",2);
            user.put("userName","游客");
            user.put("email","空");
            user.put("timeStamp",System.currentTimeMillis());
            UserHolder.saveUser(user);

        }else {
            Map<String, Object> user = TokenUtil.parseToken(token);
            UserHolder.saveUser(user);
        }
        return true;
    }
}
