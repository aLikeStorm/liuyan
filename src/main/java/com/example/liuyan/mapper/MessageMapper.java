package com.example.liuyan.mapper;

import com.example.liuyan.entity.dto.MessageAndUserDTO;
import com.example.liuyan.entity.dto.MessageOutlineDTO;
import com.example.liuyan.entity.pojo.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 17:30
 * @packagename com.example.liuyan.mapper
 * @classname MessageMapper
 * @description
 */
@Component
@Mapper
public interface MessageMapper {
    Integer insertMessage(Message message);

    @Select("SELECT * FROM tb_message WHERE id = #{id}")
    Message findmeeageById(@Param("id") Integer id);

    @Delete("DELETE FROM tb_message WHERE id=#{id}")
    Integer deleteById(@Param("id") Integer id);

    Integer deleteReply(@Param("id") Integer id);

    List<MessageOutlineDTO> findAllMessageOutlineByUser(String username);
    List<MessageOutlineDTO> findAllMessageOutlineByTopic(String topic);

    List<MessageAndUserDTO> findReply(List rids);

    @Update("UPDATE tb_message SET views = views + 1 WHERE id=#{id}")
    void addView(@Param("id") Integer id);

    Integer findMessageId(Message reply);
}
