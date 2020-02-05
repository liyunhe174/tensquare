package com.tensquare.friend.execption;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 22:12
 * @Description: 好友消息异常
 */

public class FriendException extends RuntimeException {

    private String message;

     public FriendException(String message) {
         super(message);
    }
}
