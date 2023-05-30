package com.example.liuyan.entity.dto;

import com.example.liuyan.entity.pojo.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 19:43
 * @packagename com.example.liuyan.entity.dto
 * @classname MessageDetaikDTO
 * @description 用于返回页面留言页面数据
 */
@Data
@AllArgsConstructor
public class MessageDetailDataDTO {
    MessageAndUserDTO message;
    List<MessageAndUserDTO> replyList;
}
