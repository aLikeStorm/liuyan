package com.example.liuyan.entity.dto;

import com.example.liuyan.entity.pojo.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 20:24
 * @packagename com.example.liuyan.entity.dto
 * @classname EmailDTO
 * @description
 */
@Data
@AllArgsConstructor
public class EmailDTO {
    private String musername;
    private String rusername;
    private Message message;
    private Message reply;
}
