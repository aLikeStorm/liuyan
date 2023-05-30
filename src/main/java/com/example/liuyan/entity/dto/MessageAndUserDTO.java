package com.example.liuyan.entity.dto;

import com.example.liuyan.entity.pojo.BaseEntity;
import com.example.liuyan.entity.pojo.Message;
import com.example.liuyan.entity.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/22 11:11
 * @packagename com.example.liuyan.entity.dto
 * @classname MessageAndUserDTO
 * @description 封装留言以及关于这个留言的用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageAndUserDTO extends BaseEntity {
    private String message;
    private Integer uid;
    private String topic;
    private Integer views;
    private Integer state;
    private String username;
    private String email;
    private Short role;
    public MessageAndUserDTO(User user, Message message) {
        this.createdate = message.getCreatedate();
        this.id = message.getId();
        this.message = message.getMessage();
        this.uid = message.getUid();
        this.topic = message.getTopic();
        this.views = message.getViews();
        this.state = message.getState();
        this.username  = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
