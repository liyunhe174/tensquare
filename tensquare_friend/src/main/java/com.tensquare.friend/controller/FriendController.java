package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.execption.FriendException;
import com.tensquare.friend.service.FriendService;
import com.tensquare.friend.service.NoFriendService;
import entity.ResultVO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import util.ResultVOUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 22:07
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private NoFriendService noFriendService;

    /**
     * 描述: 添加好友或者非好友的方法
     * @param friendid:  好友的id
     * @param type:   添加的类型
     * @param request: 请求对象由于获取token数据
     * @return entity.ResultVO
     * @author  liyunhe
     * @Datetime  2020/1/29 22:22
     */
    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public ResultVO like(@PathVariable String friendid, @PathVariable String type, HttpServletRequest request) {

        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            throw new FriendException("权限不足");
        }

        if (type != null) {
            if ("1".equals(type)) {
                //添加好友操作
                if (friendService.addFriend(claims.getId(), friendid) == 0) {
                    throw new FriendException("添加失败");
                }
            }
            if ("2".equals(type)) {
                //添加非好友
                noFriendService.addNoFriend(claims.getId(), friendid);
            }else {
                throw new FriendException("参数异常");
            }
        }
        return ResultVOUtil.success("操作成功");
    }

    /**
     * 描述: 删除好友的方法
     * @param friendid:
     * @param request:
     * @return entity.ResultVO
     * @author  liyunhe
     * @Datetime  2020/1/29 22:48
     */
    @RequestMapping(value = "/{friendid}", method = RequestMethod.DELETE)
    public ResultVO deleteFriend(@PathVariable String friendid, HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            throw new FriendException("权限不足");
        }
        if (StringUtils.isEmpty(friendid)) {
            throw new FriendException("参数异常");
        }
        friendService.deleteFriend(claims.getId(), friendid);
        return ResultVOUtil.success("操作成功");
    }
}
