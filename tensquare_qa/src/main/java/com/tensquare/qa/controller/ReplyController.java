package com.tensquare.qa.controller;
import java.util.List;
import java.util.Map;

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

import com.tensquare.qa.pojo.Reply;
import com.tensquare.qa.service.ReplyService;

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
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResultVO findAll(){
		return new ResultVO(true,StatusCode.OK,"查询成功",replyService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResultVO findById(@PathVariable String id){
		return new ResultVO(true,StatusCode.OK,"查询成功",replyService.findById(id));
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
		Page<Reply> pageList = replyService.findSearch(searchMap, page, size);
		return  new ResultVO(true,StatusCode.OK,"查询成功",  new PageResult<Reply>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResultVO findSearch( @RequestBody Map searchMap){
        return new ResultVO(true,StatusCode.OK,"查询成功",replyService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param reply
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResultVO add(@RequestBody Reply reply  , HttpServletRequest request){
		Claims claims = (Claims) request.getAttribute("user_admin");
		if (claims == null) {
			return ResultVOUtil.error("权限不足");
		}
		replyService.add(reply);
		return new ResultVO(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param reply
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResultVO update(@RequestBody Reply reply, @PathVariable String id ){
		reply.setId(id);
		replyService.update(reply);		
		return new ResultVO(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResultVO delete(@PathVariable String id ){
		replyService.deleteById(id);
		return new ResultVO(true,StatusCode.OK,"删除成功");
	}
	
}
