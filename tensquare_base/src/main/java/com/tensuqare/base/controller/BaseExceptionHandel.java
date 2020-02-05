package com.tensuqare.base.controller;

import entity.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import util.ResultVOUtil;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/19 13:02
 * @Description: 全局异常处理
 */
@RestControllerAdvice
public class BaseExceptionHandel {

    @ExceptionHandler(value = Exception.class)
    public ResultVO exception(Exception e) {
        e.printStackTrace();
        return ResultVOUtil.error(e.getMessage());
    }

}
