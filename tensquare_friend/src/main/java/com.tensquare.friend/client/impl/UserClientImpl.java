package com.tensquare.friend.client.impl;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.execption.FriendException;
import org.springframework.stereotype.Component;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/30 22:09
 * @Description: 熔断器
 */
@Component
public class UserClientImpl implements UserClient {


    @Override
    public void incfans(String userid, int x) {
        System.out.println("熔断器启动");

        throw new FriendException("用户服务器出现异常，无法调用");
    }

    @Override
    public void incfollow(String userid, int x) {
        throw new FriendException("用户服务器出现异常，无法调用");
    }
}
