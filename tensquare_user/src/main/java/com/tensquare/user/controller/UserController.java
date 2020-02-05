package com.tensquare.user.controller;
import java.util.HashMap;
import java.util.Map;

import entity.PageResult;
import io.jsonwebtoken.Claims;
import io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import entity.ResultVO;
import entity.StatusCode;
import util.JwtUtil;
import util.ResultVOUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private JwtUtil jwtUtil;



	/**
	 * 描述: 修改用户的粉丝数
	 * @param userid:  用户的id
	 * @param x:  修改的数量
	 * @return void
	 * @author  liyunhe
	 * @Datetime  2020/1/30 20:35
	 */
	@RequestMapping(value = "/incfans/{userid}/{x}", method = RequestMethod.PUT)
	public void incfans(@PathVariable("userid") String userid, @PathVariable("x") int x) {
		userService.updateFanscount(userid, x);
	}

	/**
	 * 描述: 修改用户的关注数
	 * @param userid:  用户id
	 * @param x:  修改的数量
	 * @return void
	 * @author  liyunhe
	 * @Datetime  2020/1/30 20:35
	 */
	@RequestMapping(value = "/incfollow/{userid}/{x}", method = RequestMethod.PUT)
	public void incfollow(@PathVariable("userid") String userid, @PathVariable("x") int x) {
		userService.updateFollowcount(userid, x);
	}

	/**
	 * 描述: 用户登录
	 * @param user:
	 * @return entity.ResultVO
	 * @author  liyunhe
	 * @Datetime  2020/1/30 20:34
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResultVO login(@RequestBody User user) {

		user = userService.login(user);
		if (user == null ) {
			return ResultVOUtil.error("用户名或者密码不正确");
		}
		String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
		Map<String , Object> map = new HashMap();
		map.put("token", token);
		map.put("roles", "user");
		map.put("name", user.getNickname());
		map.put("avatar", user.getAvatar());
		return ResultVOUtil.success("登录成功",map);

	}
	/**
	 * 发送验证码操作
	 * @param mobile  用户的手机号
	 * @return
	 */
	@RequestMapping(value = "/sendsms/{mobile}" , method = RequestMethod.POST)
	public ResultVO sendsms(@PathVariable String mobile) {
		userService.sendSms(mobile);
		return ResultVOUtil.success("发送成功");
	}


	/**
	 *  用户的注册方法
	 * @param code
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register/{code}" , method = RequestMethod.POST)
	public ResultVO register(@PathVariable String code, @RequestBody User user) {

		if (StringUtils.isEmpty(code)) {
			return ResultVOUtil.error("用户验证码不能为空");
		}
		//从缓冲中获取验证码
		String checkcode = (String) redisTemplate.opsForValue().get("checkcode_" + user.getMobile());

		if (!StringUtils.equals(checkcode, code)) {
			return ResultVOUtil.error("用户输入的验证码不正确");
		}
		userService.add(user);
		return ResultVOUtil.success("用户注册成功");
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResultVO findAll(){
		return new ResultVO(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ResultVO findById(@PathVariable String id){
		return new ResultVO(true,StatusCode.OK,"查询成功",userService.findById(id));
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
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new ResultVO(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public ResultVO findSearch( @RequestBody Map searchMap){
        return new ResultVO(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResultVO add(@RequestBody User user  ){
		userService.add(user);
		return new ResultVO(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public ResultVO update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new ResultVO(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public ResultVO delete(@PathVariable String id , HttpServletRequest request){
		Claims claims = (Claims) request.getAttribute("admin_claims");

		if (claims == null) {
			return ResultVOUtil.error("权限不足");
		}
		userService.deleteById(id);
		return new ResultVO(true,StatusCode.OK,"删除成功");
	}
	
}
