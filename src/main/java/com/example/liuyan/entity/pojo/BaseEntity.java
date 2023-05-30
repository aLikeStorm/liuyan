package com.example.liuyan.entity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 13:06
 * @packagename com.example.liuyan.entity.pojo
 * @classname BaseEntity
 * @description 实体类的基类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {
    public Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    public Date createdate;
}
