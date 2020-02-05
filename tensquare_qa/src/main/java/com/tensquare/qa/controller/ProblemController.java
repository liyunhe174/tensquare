package com.tensquare.qa.controller;
import java.util.Map;

import com.tensquare.qa.client.LabelClient;
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

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

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
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;
	@Autowired
	private LabelClient labelClient;


	/**
	 * 通过客户端进行远程调用标签的根据id查找标签的方法
	 * @param labelId
	 * @return
	 */
	@RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
	public  ResultVO findByid(@PathVariable String labelId) {
		return labelClient.findByid(labelId);
	}


	@RequestMapping(value = "/newlist/{labelId}/{page}/{size}", method = RequestMethod.GET)
	public ResultVO newList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
		Page<Problem> problemPage = problemService.newList(labelId, page, size);

		return ResultVOUtil.success("查询成功"
				, new PageResult<Problem>(problemPage.getTotalElements(), problemPage.getContent()));
	}


	@RequestMapping(value = "/hotlist/{labelId}/{page}/{size}", method = RequestMethod.GET)
	public ResultVO hotList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
		Page<Problem> problemPage = problemService.hotList(labelId, page, size);

		return ResultVOUtil.success("查询成功"
				, new PageResult<Problem>(problemPage.getTotalElements(), problemPage.getContent()));

	}


	@RequestMapping(value = "/waitlist/{labelId}/{page}/{size}", method = RequestMethod.GET)
	public ResultVO waitList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {

		Page<Problem> problemPage = problemService.waitList(labelId, page, size);

		return ResultVOUtil.success("查询成功"
				, new PageResult<Problem>(problemPage.getTotalElements(), problemPage.getContent()));

	}



	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResultVO findAll(){
		return new ResultVO(true,StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResultVO findById(@PathVariable String id){
		return new ResultVO(true,StatusCode.OK,"查询成功",problemService.findById(id));
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
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new ResultVO(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResultVO findSearch( @RequestBody Map searchMap){
        return new ResultVO(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResultVO add(@RequestBody Problem problem  , HttpServletRequest request){
		Claims claims = (Claims) request.getAttribute("user_claims");
		if (claims == null) {
			return ResultVOUtil.error("权限不足");
		}
		problem.setUserid(claims.getId());
		problemService.add(problem);
		return new ResultVO(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResultVO update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new ResultVO(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResultVO delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new ResultVO(true,StatusCode.OK,"删除成功");
	}
	
}
