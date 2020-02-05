package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 21:40
 * @Description: 非好友dao
 */
public interface NoFriendRepository  extends JpaRepository<NoFriend,String> {
    /**
     * 描述: 查找非好友信息
     * @param userid:
     * @param friendid:
     * @return com.tensquare.friend.pojo.NoFriend
     * @author  liyunhe
     * @Datetime  2020/1/29 22:29
     */
    NoFriend findByUseridAndFriendid(String userid, String friendid);
}
