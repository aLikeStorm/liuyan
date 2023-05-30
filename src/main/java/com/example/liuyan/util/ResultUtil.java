package com.example.liuyan.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 13:24
 * @packagename com.example.liuyan.util
 * @classname ResultUtil
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultUtil {
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;

    public static ResultUtil ok(){
        return new ResultUtil(true, null, null, null);
    }
    public static ResultUtil ok(Object data){
        return new ResultUtil(true, null, data, null);
    }
    public static ResultUtil ok(List<?> data, Long total){
        return new ResultUtil(true, null, data, total);
    }
    public static ResultUtil fail(String errorMsg){
        return new ResultUtil(false, errorMsg, null, null);
    }
}
