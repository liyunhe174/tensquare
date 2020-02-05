package com.tensquare.recruit.controller;
import java.util.List;
import java.util.Map;

import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;

import entity.ResultVO;
import entity.StatusCode;
import util.ResultVOUtil;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class RecruitController {

	@Autowired
	private RecruitService recruitService;



	/**
	 * 描述: 查询最新的招聘信息
	 * @return entity.ResultVO
	 * @author  liyunhe
	 * @Datetime  2020/1/19 16:32
	 */
	@RequestMapping(value = "/search/newlist",method = RequestMethod.GET)
	public ResultVO newList() {
		return ResultVOUtil.success("查询成功", recruitService.findByStateIsNot0());
	}

	/**
	 * 描述: 查询推荐的招聘信息
	 * @return entity.ResultVO
	 * @author  liyunhe
	 * @Datetime  2020/1/19 16:30
	 */
	@RequestMapping(value = "/search/recommend",method = RequestMethod.GET)
	public ResultVO recommend() {
		return ResultVOUtil.success("查询成功", recruitService.findByStateIs2());
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResultVO findAll(){
		return new ResultVO(true,StatusCode.OK,"查询成功",recruitService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResultVO findById(@PathVariable String id){
		return new ResultVO(true,StatusCode.OK,"查询成功",recruitService.findById(id));
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
		Page<Recruit> pageList = recruitService.findSearch(searchMap, page, size);
		return  new ResultVO(true,StatusCode.OK,"查询成功",  new PageResult<Recruit>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResultVO findSearch( @RequestBody Map searchMap){
        return new ResultVO(true,StatusCode.OK,"查询成功",recruitService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param recruit
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResultVO add(@RequestBody Recruit recruit  ){
		recruitService.add(recruit);
		return new ResultVO(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param recruit
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResultVO update(@RequestBody Recruit recruit, @PathVariable String id ){
		recruit.setId(id);
		recruitService.update(recruit);		
		return new ResultVO(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResultVO delete(@PathVariable String id ){
		recruitService.deleteById(id);
		return new ResultVO(true,StatusCode.OK,"删除成功");
	}
	
}
