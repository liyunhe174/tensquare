package com.tensquare.friend.service;

import com.tensquare.friend.dao.NoFriendRepository;
import com.tensquare.friend.execption.FriendException;
import com.tensquare.friend.pojo.NoFriend;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 22:27
 * @Description:  非好友的服务层
 */
@Service
@Transactional
public class NoFriendService  {
    @Autowired
    private NoFriendRepository repository;

    /**
     * 描述: 添加非好友
     * @param userid: 用户id
     * @param friendid:  好友id
     * @return int
     * @author  liyunhe
     * @Datetime  2020/1/29 22:31
     */
    public int addNoFriend(String userid, String friendid) {
        NoFriend noFriend = repository.findByUseridAndFriendid(userid, friendid);
        if (noFriend != null) {
            throw new FriendException("已经是非好友了");
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        repository.save(noFriend);
        return 1;
    }
}
