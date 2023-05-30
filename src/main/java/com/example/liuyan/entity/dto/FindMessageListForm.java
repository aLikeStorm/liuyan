package com.example.liuyan.entity.dto;

import lombok.Data;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 19:08
 * @packagename com.example.liuyan.entity.dto
 * @classname FindMessageListForm
 * @description 查找留言的表单
 */
@Data
public class FindMessageListForm {
    private Short type; //查找留言的方式，根据作者0，主题关键字1
    private String info;  //查找的信息
    private Short sort; // 根据什么排序 回复数量0，浏览数量1,根据更新时间 2
}
