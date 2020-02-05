package com.tenssquare.spit.controller;

import com.tenssquare.spit.pjo.Spit;
import com.tenssquare.spit.service.SpitService;
import entity.PageResult;
import entity.ResultVO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.ResultVOUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/22 12:32
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加吐槽方法
     * @param spit 吐槽的实体类
     * @param request 请求
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultVO save(@RequestBody Spit spit , HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return ResultVOUtil.error("权限不足");
        }
        Spit save = spitService.save(spit);
        if (save != null) {
            return ResultVOUtil.success("添加成功");
        }
        return ResultVOUtil.error("添加失败");
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResultVO findAll() {
        List<Spit> all = spitService.findAll();
        return ResultVOUtil.success("查询成功", all);
    }


    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public ResultVO findById(@PathVariable  String spitId) {
        Spit one = spitService.findOne(spitId);
        return ResultVOUtil.success("查询成功", one);
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public ResultVO update(@PathVariable String spitId,@RequestBody Spit spit) {
        spit.set_id(spitId);
        Spit update = spitService.update(spit);
        return ResultVOUtil.success("修改成功");
    }


    @RequestMapping(value = "/{spitId}" , method = RequestMethod.DELETE)
    public ResultVO delete(String spitId) {
        spitService.delete(spitId);
        return ResultVOUtil.success("删除成功");
    }

    @RequestMapping(value = "/{parentid}/{page}/{size}")
    public ResultVO findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        Page<Spit> byParentid = spitService.findByParentid(parentid, page, size);

        return ResultVOUtil.success("查询成功", new PageResult<Spit>(byParentid.getTotalElements(), byParentid.getContent()));
    }


    @RequestMapping(value = "/thumbup/{spitId}" , method = RequestMethod.PUT)
    public ResultVO updateThumbup(@PathVariable String spitId) {
        //TODO 用户的id要从session中取
        String userId = "123456";

        if (redisTemplate.opsForValue().get("thumbup:" + userId + ":" + spitId) != null) {
            return ResultVOUtil.error("您已经点过赞了");
        }
        spitService.updateThumup(spitId);
        redisTemplate.opsForValue().set("thumbup:" + userId + ":" + spitId, "1");
        return ResultVOUtil.success("点赞成功");

    }


}
