package com.tenssquare.spit.pjo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/22 12:15
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spit implements Serializable {

    @Id     
    private String _id;     
    private String content;     
    private Date publishtime;
    private String userid;     
    private String nickname;     
    private Integer visits;     
    private Integer thumbup;     
    private Integer share;     
    private Integer comment;     
    private String state;     
    private String parentid;

}
