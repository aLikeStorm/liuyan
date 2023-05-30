package com.example.liuyan.util;

import com.example.liuyan.entity.dto.UserDTO;

import java.util.Map;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 16:46
 * @packagename com.example.liuyan.util
 * @classname UserHolder
 * @description
 */
public class UserHolder {
    private static final ThreadLocal<Map> tl = new ThreadLocal<>();

    public static void saveUser(Map user){
        tl.set(user);
    }

    public static Map getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
