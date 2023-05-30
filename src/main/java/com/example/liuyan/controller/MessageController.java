package com.example.liuyan.controller;

import com.example.liuyan.entity.dto.FindMessageListForm;
import com.example.liuyan.entity.pojo.Message;
import com.example.liuyan.server.IMessageService;
import com.example.liuyan.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 17:37
 * @packagename com.example.liuyan.controller
 * @classname MessageController
 * @description
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    IMessageService messageService;
    @PostMapping("/add")
    public ResultUtil addMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }
    @GetMapping("/delete/{id}")
    public ResultUtil delete(@PathVariable("id")Integer id) {
        return messageService.deleteById(id);
    }
    @PostMapping("/findAll")
    public ResultUtil findAll(@RequestBody FindMessageListForm form) {
        return messageService.findAllMessageOutline(form);
    }

    @GetMapping("/findMessageDetail/{id}")
    public ResultUtil findMessageDetail(@PathVariable("id") Integer id){
        return messageService.findMessageDetail(id);
    }

    @PostMapping("/addReply/{mid}/{isEmail}") //是否邮件通知他，如果邮件通知则为1，不通知为0
    public ResultUtil addReply(@PathVariable("mid")Integer mid,
                               @PathVariable("isEmail") Integer isEmail,
                               @RequestBody Message reply) {
        return messageService.addReply(mid,isEmail,reply);
    }

}
