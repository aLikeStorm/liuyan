package com.example.liuyan.config;

import com.example.liuyan.util.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 安克松
 * @version 1.0.0
 * @date 2023/4/21 17:02
 * @packagename com.example.liuyan.config
 * @classname ExceptionHandler
 * @description 全局异常处理器
 */
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResultUtil globalExceptionHandler(Exception e) {
        return ResultUtil.fail(e.getMessage());
    }
}
