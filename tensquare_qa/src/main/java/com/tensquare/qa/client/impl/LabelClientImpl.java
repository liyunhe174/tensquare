package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.LabelClient;
import entity.ResultVO;
import org.springframework.stereotype.Component;
import util.ResultVOUtil;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/30 22:04
 * @Description:
 */
@Component
public class LabelClientImpl implements LabelClient {

    @Override
    public ResultVO findByid(String labelId) {
        return ResultVOUtil.error("熔断器启动");
    }
}
