package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 21:37
 * @Description:
 */
@Entity
@Table(name = "tb_nofriend")
@Data
@IdClass(NoFriend.class)
public class NoFriend  implements Serializable {
    @Id
    private String userid;
    @Id
    private String friendid;
}
