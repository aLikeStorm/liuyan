package com.example.liuyan.entity.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 13:04
 * @packagename com.example.liuyan.entity.pojo
 * @classname User
 * @description
 */
@Data
public class User extends BaseEntity implements Serializable {
    private String username;
    private String password;
    private String email;
    private Short state;
    private Short role;
}
