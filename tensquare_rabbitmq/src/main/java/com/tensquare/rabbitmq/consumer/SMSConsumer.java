package com.tensquare.rabbitmq.consumer;

import com.tensquare.rabbitmq.utils.SmsSend;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/27 15:31
 * @Description: 短信监控方法
 */
@Component
@RabbitListener(queues = "itcast")
public class SMSConsumer {

    @Autowired
    private SmsSend smsSend; //阿里云的短信服务的封装类



    @RabbitHandler
    public void smsConsumer(Map<String, String> map) {
        System.out.println("电话号码：" + map.get("mobile"));
        System.out.println("验证码：" + map.get("checkcode"));
        smsSend.sendSms(map.get("mobile"),map.get("checkcode"));

    }

}
