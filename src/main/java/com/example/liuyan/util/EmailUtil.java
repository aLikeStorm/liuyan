package com.example.liuyan.util;

import com.example.liuyan.entity.dto.EmailDTO;
import com.example.liuyan.entity.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 20:19
 * @packagename com.example.liuyan.util
 * @classname EmailUtil
 * @description
 */
@Component
public class EmailUtil {
    private final static String them = "留言簿消息";
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;
    public void sendMail(String emailTo, EmailDTO emailDTO) {
        try{

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailTo);
            message.setSubject(them);
            message.setText(textInfo(emailDTO));
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String textInfo(EmailDTO emailDTO) {
        return "你好,"+emailDTO.getMusername()+":"+"\n\t你在我们留言网站上关于 \""
                +emailDTO.getMessage().getTopic()+"\" 已经有人回复啦。\n\t回复人："
                +emailDTO.getRusername()+"。\n\t他的回复主题是:"+emailDTO.getReply().getTopic()
                +"\n\t回复内容为:"+emailDTO.getReply().getMessage()
                +"\n\t快去看看吧！";

    }
}
