package com.tensuqare.base.service;

import com.tensuqare.base.pjo.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/19 11:43
 * @Description: label服务层接口
 */
public interface LabelService {

    /**
     * @Description  标签添加方法
     * @param label:  标签实体类
     * @return com.tensuqare.base.pjo.Label
     * @author  liyunhe
     * @Datetime  2020/1/19 11:44
     */
    Label save(Label label);

    /**
     * @Description 标签的修改方法
     * @param label 标签实体类
     * @return com.tensuqare.base.pjo.Label
     * @author  liyunhe
     * @Datetime  2020/1/19 11:45
     */
    Label update(Label label);


    /**
     *  根据id查询标签信息
     * @param labelId:  标签id
     * @return com.tensuqare.base.pjo.Label
     * @author  liyunhe
     * @Datetime  2020/1/19 11:46
     */
    Label findById(String labelId);


    /**
     *  查询所有标签信息
     * @return java.util.List<com.tensuqare.base.pjo.Label>
     * @author  liyunhe
     * @Datetime  2020/1/19 11:48
     */
    List<Label> findAll();



    /**
     * 描述: 根据id删除标签
     * @param labelId:
     * @return void
     * @author  liyunhe
     * @Datetime  2020/1/19 12:01
     */
    void delete(String labelId);

    List<Label> search(Label label);


    Page<Label> pageSearch(Label label, int page, int size);
}
