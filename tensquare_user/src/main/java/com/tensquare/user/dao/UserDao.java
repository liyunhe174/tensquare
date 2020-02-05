package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    /**
     * 根据手机查询用户信息
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);


    /**
     * 描述: 更改粉丝数
     * @param userid:  用户的id
     * @param x:  更改数量
     * @return void
     * @author  liyunhe
     * @Datetime  2020/1/30 20:21
     */
    @Modifying
    @Query(value = "UPDATE tb_user SET fanscount = fanscount + ?2  WHERE id = ?1" , nativeQuery = true)
    void updateFanscount(String userid, int x);


    /**
     * 描述:  更改关注数
     * @param userid:  用户id
     * @param x:  修改得数量
     * @return void
     * @author  liyunhe
     * @Datetime  2020/1/30 20:22
     */
    @Modifying
    @Query(value = "UPDATE tb_user SET followcount = followcount + ?2 WHERE id = ?1" , nativeQuery = true)
    void updateFollowcount(String userid, int x);




}
