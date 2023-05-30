package com.example.liuyan.controller;

import com.example.liuyan.entity.pojo.User;
import com.example.liuyan.server.IUserService;
import com.example.liuyan.util.ConstantUtil;
import com.example.liuyan.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 16:52
 * @packagename com.example.liuyan.controller
 * @classname UserController
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @PostMapping("/login")
    public ResultUtil login(@RequestBody User user) {

        return userService.login(user);
    }
    @PostMapping("/register")
    public ResultUtil register(@RequestBody User user) {
        return userService.register(user);
    }
    @GetMapping("/logout")
    public ResultUtil logout(HttpSession session) {
        session.removeAttribute("user");
        return ResultUtil.ok();
    }

    /**
     * 获取公告
     * @return
     */
    @GetMapping("/announcement")
    public ResultUtil getAnnouncement() {
        return ResultUtil.ok(ConstantUtil.ANNOUNCEMENT);
    }

}
