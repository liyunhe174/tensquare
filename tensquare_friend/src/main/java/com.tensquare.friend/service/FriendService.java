package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendRepository;
import com.tensquare.friend.execption.FriendException;
import com.tensquare.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 21:41
 * @Description:
 */
@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendRepository repository;

    @Autowired
    private NoFriendService noFriendService;
    @Autowired
    private UserClient userClient;

    /**
     * 描述:  添加好友
     * @param userid:  用户id
     * @param friendid:  好友id
     * @return int   返回添加的状态
     * @author  liyunhe
     * @Datetime  2020/1/29 22:04
     */
    public int addFriend(String userid, String friendid) {
        //1、 查询是有已经添加过好友
        Friend friend = repository.findByUseridAndFriendid(userid, friendid);
        if (friend != null) {
            throw new FriendException("已经添加过好友了");
        }
        //2、 如果没有添加过好友 进行好友添加
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        Friend save = repository.save(friend);
        userClient.incfans(friendid, 1);
        userClient.incfollow(userid, 1);
        if (save == null) {
            return 0;
        }
        //3、如果对方也添加你好友就修改好友的状态
        if (repository.findByUseridAndFriendid(friendid, userid) != null) {
            repository.updateIsLike("1", userid, friendid);
            repository.updateIsLike("1", friendid  , userid);
        }
        return 1;
    }


    /**
     * 描述: 删除好友
     * @param userid:
     * @param friendid:
     * @return void
     * @author  liyunhe
     * @Datetime  2020/1/29 22:41
     */
    public void deleteFriend(String userid, String friendid) {
        userClient.incfans(friendid, -1);
        userClient.incfollow(userid, -1);
        //删除用户的好友
        repository.deleteByUseridAndFriendid(userid, friendid);

        //修改好友方的状态
        repository.updateIsLike("0", friendid, userid);
        //添加为非好友
        noFriendService.addNoFriend(userid, friendid);

    }


}
