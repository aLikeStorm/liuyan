package com.example.liuyan.server;

import com.example.liuyan.entity.pojo.User;
import com.example.liuyan.util.ResultUtil;

import javax.servlet.http.HttpSession;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 13:23
 * @packagename com.example.liuyan.server
 * @classname IUserService
 * @description
 */
public interface IUserService {
    ResultUtil login(User user);
    ResultUtil register(User user);
}
