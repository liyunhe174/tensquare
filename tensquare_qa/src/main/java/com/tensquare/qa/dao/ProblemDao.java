package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query(value =
            "select * from tb_problem tp , tb_pl tpl where tp.id = tpl.problemid and tpl.labelid =? order by tp.createtime desc "
            ,nativeQuery = true)
    Page<Problem> newList(String labelId, Pageable pageable);


    @Query(value =
            "select * from tb_problem tp , tb_pl tpl where tp.id = tpl.problemid and tpl.labelid =? order by reply desc "
            ,nativeQuery = true)
    Page<Problem> hotList(String labelId, Pageable pageable);


    @Query(value =
            "select * from tb_problem tp , tb_pl tpl where tp.id = tpl.problemid and tpl.labelid = ?1 and tp.solve = ?2"
            ,nativeQuery =  true)
    Page<Problem> waitList(String labelId ,String solve , Pageable pageable);

}
