package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    /**
     * 描述: 查询推荐的信息
     * @param state: 信息的状态
     * @return java.util.List<com.tensquare.recruit.pojo.Recruit>
     * @author  liyunhe
     * @Datetime  2020/1/19 16:24
     */
    public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);

    /**
     * 描述: 查询出最新的招聘信息
     * @param state: 信息的状态
     * @return java.util.List<com.tensquare.recruit.pojo.Recruit>
     * @author  liyunhe
     * @Datetime  2020/1/19 16:24
     */
    public List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);
}
