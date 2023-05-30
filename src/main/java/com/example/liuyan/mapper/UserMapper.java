package com.example.liuyan.mapper;

import com.example.liuyan.entity.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/20 10:55
 * @packagename com.example.liuyan.mapper
 * @classname UserMapper
 * @description
 */
@Mapper
@Component
public interface UserMapper {
    @Select("SELECT * FROM tb_user")
    List<User> findAllUsers();

    @Select("SELECT username FROM tb_user WHERE id=#{id}")
    String findUserById(@Param("id") Integer id);
    @Select("SELECT * FROM tb_user WHERE id=#{id}")
    User getUser(@Param("id") Integer id);

    User findUserByUsername(String username);
    Integer insertUser(User user);
}
