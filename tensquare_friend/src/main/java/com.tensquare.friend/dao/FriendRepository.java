package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 21:39
 * @Description: 好友dao
 */
public interface FriendRepository  extends JpaRepository<Friend ,String>  {

    Friend findByUseridAndFriendid(String userid, String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend SET islike = ?1 WHERE userid = ?2 AND friendid = ?3",nativeQuery = true)
    void updateIsLike(String islike, String userid, String friendid);

    @Modifying
    @Query(value = "DELETE FROM tb_friend WHERE userid=?1 AND friendid = ?2", nativeQuery = true)
    void deleteByUseridAndFriendid(String userid, String friendid);
}
