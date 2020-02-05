package com.tensquare.qa.client;

import com.tensquare.qa.client.impl.LabelClientImpl;
import entity.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 20:48
 * @Description:  通过feign进行远程调用 标签服务
 */
@Component
@FeignClient(value = "tensquare-base" , fallback = LabelClientImpl.class)
public interface LabelClient {

    /**
     * 标签的根据id查找标签的方法
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
    ResultVO findByid(@PathVariable String labelId);

}
