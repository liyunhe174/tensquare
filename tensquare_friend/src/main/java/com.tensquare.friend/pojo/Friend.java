package com.tensquare.friend.pojo;

import lombok.Data;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 21:35
 * @Description:
 */
@Entity
@Table(name = "tb_friend")
@Data
@IdClass(Friend.class)
public class Friend implements Serializable {

    @Id
    private String userid;
    @Id
    private String friendid;

    private String islike;
}
