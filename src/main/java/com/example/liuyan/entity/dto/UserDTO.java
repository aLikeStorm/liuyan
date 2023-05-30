package com.example.liuyan.entity.dto;

import com.example.liuyan.entity.pojo.User;
import lombok.Data;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 13:36
 * @packagename com.example.liuyan.entity.dto
 * @classname UserDTO
 * @description
 */
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private Short state;
    private Short role;
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
