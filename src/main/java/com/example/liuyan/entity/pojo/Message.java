package com.example.liuyan.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.server.session.InMemoryWebSessionStore;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 13:09
 * @packagename com.example.liuyan.entity.pojo
 * @classname Message
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity implements Serializable {
    private String message;
    private Integer uid;
    private String topic;
    private Integer views;
    private Integer state; // 表示是否是回复留言，不是为0，是为1
}
