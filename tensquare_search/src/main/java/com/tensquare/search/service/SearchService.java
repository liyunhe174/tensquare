package com.tensquare.search.service;

import com.tensquare.search.dao.SearchRepository;
import com.tensquare.search.pjo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/23 18:10
 * @Description:
 */
@Service
public class SearchService {

    @Autowired
    private SearchRepository repository;


    @Autowired
    private IdWorker idWorker;
    /**
     * 描述: 插入方法
     * @param article:
     * @return com.tensquare.search.pjo.Article
     * @author  liyunhe
     * @Datetime  2020/1/23 18:12
     */
    public Article save(Article article) {
        article.setId(idWorker.nextId()+"");
        return repository.save(article);
    }


    /**
     * 描述: 根据key进行主题和内容的搜索
     * @param key:
     * @param page:
     * @param size:
     * @return org.springframework.data.domain.Page<com.tensquare.search.pjo.Article>
     * @author  liyunhe
     * @Datetime  2020/1/24 17:39
     */
    public Page<Article> findByTitleOrContentLike(String key, int page, int size) {
        return repository.findByTitleOrContentLike(key, key, PageRequest.of(page - 1, size));
    }

}
