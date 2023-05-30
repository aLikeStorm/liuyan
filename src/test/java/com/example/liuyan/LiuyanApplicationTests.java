package com.example.liuyan;

import com.example.liuyan.entity.pojo.Message;
import com.example.liuyan.mapper.MessageMapper;
import com.example.liuyan.mapper.UserMapper;
import com.example.liuyan.server.IMessageService;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.sql.DataSource;
import java.util.Date;

@SpringBootTest
class LiuyanApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println(dataSource);
    }

    @Test
    public void sendMail(){
        try{

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("2039323538@qq.com");
            message.setTo("kesong.an.work@outlook.com");
            message.setSubject("你好");
            message.setText("这是一封测试邮件");
            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void mybatisTest() {
        System.out.println(userMapper.findUserByUsername("1"));
    }

    @Test
    public void testInsert() {
        Message message = new Message();
        message.setUid(2);
        message.setCreatedate(new Date());
        message.setMessage("回复测试");
        message.setTopic("测试");
        message.setState(1);
        Integer integer = messageMapper.insertMessage(message);
        System.out.println(message.getId()+"=============================");

    }
}
