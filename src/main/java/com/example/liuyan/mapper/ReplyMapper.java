package com.example.liuyan.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 17:58
 * @packagename com.example.liuyan.mapper
 * @classname ReplyMapper
 * @description
 */
@Mapper
@Component
public interface ReplyMapper {

    @Select("SELECT COUNT(id) FROM tb_reply WHERE mid=#{mid}")
    Integer sumReply(@Param("mid") Integer mid);

    @Delete("DELETE FROM tb_reply WHERE mid=#{mid}")
    Integer deleteReply(@Param("mid") Integer mid);

    @Select("SELECT rid FROM tb_reply WHERE createdate >= #{date} ORDER BY createdate DESC LIMIT 1")
    Integer findMessageOld(@Param("date") Date date);

    @Select("SELECT rid FROM tb_reply WHERE mid=#{mid}")
    List<Integer> findAllReply(@Param("mid") Integer id);

    @Insert("INSERT INTO tb_reply(mid, rid, createdate) VALUES (#{mid},#{rid},now())")
    Integer insertReply(@Param("mid") Integer mid, @Param("rid") Integer rid);
}
