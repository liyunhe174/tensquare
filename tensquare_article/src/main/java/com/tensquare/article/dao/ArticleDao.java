package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 描述: 修改审核状态
     * @param id:
     * @return void
     * @author  liyunhe
     * @Datetime  2020/1/21 10:19
     */
    @Modifying
    @Query(value = "update tb_article set state = '1' where id = ?1" , nativeQuery = true)
    public void updateState(String id);

    /**
     * 描述:   增加点赞
     * @param id:
     * @return void
     * @author  liyunhe
     * @Datetime  2020/1/21 10:19
     */
    @Modifying
    @Query(value = "update tb_article set thumbup=thumbup+1 where id = ?1" , nativeQuery = true)
    public void addThumbup(String id);
}
