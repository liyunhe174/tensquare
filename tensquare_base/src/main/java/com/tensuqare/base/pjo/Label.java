package com.tensuqare.base.pjo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/18 22:44
 * @Description: 标签实体类
 */
@Entity
@Table(name = "tb_label")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Label implements Serializable {

    @Id
    private  String id;
    private String labelname; //标签名称
    private String state; // 状态
    private Long count ; // 使用数量
    private Long fans ; //关注数
    private String recommend ; // 是否推荐


}
