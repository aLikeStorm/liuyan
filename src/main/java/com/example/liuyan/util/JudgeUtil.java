package com.example.liuyan.util;

import com.example.liuyan.entity.pojo.User;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/22 12:57
 * @packagename com.example.liuyan.util
 * @classname JudgeUtil
 * @description
 */

public class JudgeUtil{
    public static boolean isLogin(User user) {
        return true;
    }
    public static boolean isEmptyUser(User user) {
        if (user == null) {
            return true;
        }
        return !((user.getUsername() != null && user.getUsername() != "") &&
                (user.getEmail() != null && user.getEmail() != "") &&
                (user.getPassword() != null && user.getPassword() != null));
    }
}
