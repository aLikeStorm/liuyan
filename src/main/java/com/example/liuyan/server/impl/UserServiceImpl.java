package com.example.liuyan.server.impl;

import com.example.liuyan.entity.dto.UserDTO;
import com.example.liuyan.entity.pojo.User;
import com.example.liuyan.mapper.UserMapper;
import com.example.liuyan.server.IUserService;
import com.example.liuyan.util.ConstantUtil;
import com.example.liuyan.util.JudgeUtil;
import com.example.liuyan.util.ResultUtil;
import com.example.liuyan.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 13:29
 * @packagename com.example.liuyan.server.impl
 * @classname UserServiceImpl
 * @description
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public ResultUtil login(User user) {
        User dataUser = userMapper.findUserByUsername(user.getUsername());
        if (dataUser == null) {
            return ResultUtil.fail(ConstantUtil.LOGIN_FAIL_USER);
        }
        if (!dataUser.getPassword().equals(user.getPassword())) {
            return ResultUtil.fail(ConstantUtil.LOGIN_FAIL_PASSWORD);
        }
        String token = TokenUtil.getToken(dataUser);
        log.info("用户: "+user.getUsername()+"登陆!"+"/t token值为："+token);
        return ResultUtil.ok(token);
    }

    @Override
    public ResultUtil register(User user) {
        if (JudgeUtil.isEmptyUser(user)) {
            return ResultUtil.fail(ConstantUtil.REGISTER_FAIL_EMPTY);
        }

        User userByUsername = userMapper.findUserByUsername(user.getUsername());
        if (!JudgeUtil.isEmptyUser(userByUsername)) {
            return ResultUtil.fail(ConstantUtil.REGISTER_FAIL_EXIST);
        }

        user.setCreatedate(new Date());
        user.setState((short) 0);
        user.setRole((short) 0);
        if (userMapper.insertUser(user) != 1) {
            throw new RuntimeException("创建用户失败");
        }
        log.info("用户: "+user.getUsername()+"注册成功!");
        return ResultUtil.ok(new UserDTO(user));
    }


}
