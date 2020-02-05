package com.tensquare.search.controller;

import com.tensquare.search.pjo.Article;
import com.tensquare.search.service.SearchService;
import entity.PageResult;
import entity.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import util.ResultVOUtil;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/23 18:13
 * @Description: 搜索的控制层
 */
@RestController
@RequestMapping("/article")
@CrossOrigin
public class SearchController {

    @Autowired
    private SearchService searchService;


    @RequestMapping(method = RequestMethod.POST)
    public ResultVO save(@RequestBody  Article article) {
        Article save = searchService.save(article);
        return ResultVOUtil.success("操作成功", save);
    }

    @RequestMapping(value = "/search/{key}/{page}/{size}")
    public ResultVO findByTitleOrContentLike(@PathVariable String key, @PathVariable int page, @PathVariable int size) {
        Page<Article> articlePage = searchService.findByTitleOrContentLike(key, page, size);
        return ResultVOUtil.success("查询成功", new PageResult<Article>(articlePage.getTotalElements(), articlePage.getContent()));

    }
}
