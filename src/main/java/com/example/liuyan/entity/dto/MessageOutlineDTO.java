package com.example.liuyan.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 18:15
 * @packagename com.example.liuyan.entity.dto
 * @classname MessageDTO
 * @description 作为首页信息列表的封装实体类
 */
@Data
public class MessageOutlineDTO {
    private String topic;
    private Integer mid;
    private Integer uid;
    private String username;
    private Integer replyNum;
    private Integer views;
    private String lastReplyName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date lastUpdateTime;
}
