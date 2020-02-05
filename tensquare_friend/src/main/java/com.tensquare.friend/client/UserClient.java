package com.tensquare.friend.client;

import com.tensquare.friend.client.impl.UserClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/30 20:36
 * @Description: 用户远程调用用户的服务
 */
@FeignClient(value = "tensquare-user" , fallback = UserClientImpl.class)
public interface UserClient {

    /**
     * 描述: 调用远程用户客户端的修改粉丝方法
     * @param userid:
     * @param x:
     * @return void
     * @author  liyunhe
     * @Datetime  2020/1/30 20:38
     */
    @RequestMapping(value = "/user/incfans/{userid}/{x}", method = RequestMethod.PUT)
    void incfans(@PathVariable("userid") String userid, @PathVariable("x") int x);


    /**
     * 描述: 调用远程用户服务的修改关注的方法
     * @param userid:
     * @param x:
     * @return void
     * @author  liyunhe
     * @Datetime  2020/1/30 20:39
     */
    @RequestMapping(value = "/user/incfollow/{userid}/{x}", method = RequestMethod.PUT)
    void incfollow(@PathVariable("userid") String userid, @PathVariable("x") int x);
}
