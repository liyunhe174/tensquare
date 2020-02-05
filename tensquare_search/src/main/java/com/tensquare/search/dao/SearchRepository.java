package com.tensquare.search.dao;

import com.tensquare.search.pjo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/23 18:09
 * @Description:
 */
public interface SearchRepository extends ElasticsearchRepository<Article , String> {

    /**
     * 描述: 根据主题或内容进行模糊查询并携带分页
     * @param title:   主题内容
     * @param content:  文章内容
     * @param pageable:  分页条件
     * @return org.springframework.data.domain.Page<com.tensquare.search.pjo.Article>
     * @author  liyunhe
     * @Datetime  2020/1/24 17:34
     */
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
