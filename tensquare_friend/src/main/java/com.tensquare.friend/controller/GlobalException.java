package com.tensquare.friend.controller;

import entity.ResultVO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import util.ResultVOUtil;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 22:16
 * @Description:  全局异常类
 */
@ControllerAdvice
@Component
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus  返回给前端的状态
    @ResponseBody
    public ResultVO clobalException(Exception e) {
        return ResultVOUtil.error(e.getMessage());
    }
}
