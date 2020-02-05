package com.tensquare.article.controller;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.regexp.internal.REUtil;
import entity.PageResult;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;

import entity.ResultVO;
import entity.StatusCode;
import util.ResultVOUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;


	@RequestMapping(value = "/examine/{articleId}",method = RequestMethod.PUT)
	public ResultVO examine(@PathVariable String articleId) {
		articleService.updateState(articleId);
		return ResultVOUtil.success("审核成功");
	}

	@RequestMapping(value = "/thumbup/{articleId}" ,method =  RequestMethod.PUT)
	public ResultVO thumbup(@PathVariable String articleId) {
		articleService.addThumbup(articleId);
		return ResultVOUtil.success("点赞成功");
	}


	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResultVO findAll(){
		return new ResultVO(true,StatusCode.OK,"查询成功",articleService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResultVO findById(@PathVariable String id){
		return new ResultVO(true,StatusCode.OK,"查询成功",articleService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public ResultVO findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Article> pageList = articleService.findSearch(searchMap, page, size);
		return  new ResultVO(true,StatusCode.OK,"查询成功",  new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResultVO findSearch( @RequestBody Map searchMap){
        return new ResultVO(true,StatusCode.OK,"查询成功",articleService.findSearch(searchMap));
    }

	/**
	 * 增加
	 *
	 * @param article
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResultVO add(@RequestBody Article article, HttpServletRequest request) {
		Claims claims = (Claims) request.getAttribute("user_claims");
		if (claims == null) {
			return ResultVOUtil.error("权限不足");
		}
		articleService.add(article);
		return new ResultVO(true, StatusCode.OK, "增加成功");
	}
	
	/**
	 * 修改
	 * @param article
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResultVO update(@RequestBody Article article, @PathVariable String id ){
		article.setId(id);
		articleService.update(article);		
		return new ResultVO(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResultVO delete(@PathVariable String id ){
		articleService.deleteById(id);
		return new ResultVO(true,StatusCode.OK,"删除成功");
	}
	
}
