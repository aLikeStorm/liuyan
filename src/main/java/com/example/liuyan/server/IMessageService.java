package com.example.liuyan.server;

import com.example.liuyan.entity.dto.FindMessageListForm;
import com.example.liuyan.entity.pojo.Message;
import com.example.liuyan.util.ResultUtil;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 17:33
 * @packagename com.example.liuyan.server
 * @classname IMessageService
 * @description
 */
public interface IMessageService {
    ResultUtil addMessage(Message message);

    ResultUtil deleteById(Integer id);

    ResultUtil findAllMessageOutline(FindMessageListForm form);

    ResultUtil findMessageDetail(Integer id);

    ResultUtil addReply(Integer mid, Integer isEmail, Message reply);
}
