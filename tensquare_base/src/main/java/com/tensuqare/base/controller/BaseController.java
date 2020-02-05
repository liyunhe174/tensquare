package com.tensuqare.base.controller;

import com.tensuqare.base.pjo.Label;
import com.tensuqare.base.service.LabelService;
import entity.PageResult;
import entity.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.ResultVOUtil;

import java.util.List;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/18 22:05
 * @Description:  标签的Controller
 */
@RestController
@RequestMapping("/label")
@CrossOrigin //解决跨域的问题
public class BaseController {

    @Autowired
    private LabelService service;


    /**
     * 描述: 查询所有标签
     * @return entity.ResultVO
     * @author  liyunhe
     * @Datetime  2020/1/19 12:05
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultVO findAll() {
        List<Label> all = service.findAll();

        return ResultVOUtil.success("查询成功", all);

    }


    /**
     * 描述: 根据标签id查询标签
     * @param labelId:
     * @return entity.ResultVO
     * @author  liyunhe
     * @Datetime  2020/1/19 12:12
     */
    @RequestMapping(value = "/{labelId}" , method = RequestMethod.GET)
    public ResultVO findByid(@PathVariable String labelId) {
        Label la = service.findById(labelId);
        if (la != null) {
            return ResultVOUtil.success("查询成功", la);
        }
        return ResultVOUtil.error("没有该标签");
    }


    /**
     * 描述: 添加标签
     * @param label:  标签实体类
     * @return entity.ResultVO
     * @author  liyunhe
     * @Datetime  2020/1/19 12:15
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultVO save(@RequestBody Label label) {
        Label save = service.save(label);
        if (save != null) {
            return ResultVOUtil.success("添加成功");
        }

        return ResultVOUtil.error("添加失败");
    }


    /**
     * 描述: 修改标签
     * @param labelId:  标签id
     * @param label:  修改内容
     * @return entity.ResultVO
     * @author  liyunhe
     * @Datetime  2020/1/19 12:35
     */
    @RequestMapping(value ="/{labelId}" ,method = RequestMethod.PUT)
    public ResultVO update(@PathVariable String labelId , @RequestBody Label label) {
        Label byId = service.findById(labelId);
        if (byId == null) {
            return ResultVOUtil.error("没有该标签");
        }
        label.setId(labelId);

        Label update = service.update(label);
        if (update == null) {
            return ResultVOUtil.error("修改失败");
        }
        return ResultVOUtil.success("修改成功");
    }


    /**
     * 描述: 删除方法
     * @param labelId:  标签id
     * @return entity.ResultVO
     * @author  liyunhe
     * @Datetime  2020/1/19 12:38
     */
    @RequestMapping(value = "/{labelId}" , method = RequestMethod.DELETE)
    public ResultVO delete(@PathVariable String labelId) {
        Label byId = service.findById(labelId);
        if (byId == null) {
            return ResultVOUtil.error("没有该标签");
        }
        service.delete(labelId);
        return ResultVOUtil.success("删除成功");
    }


    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResultVO search(@RequestBody Label label) {

        List<Label> search = service.search(label);

        return ResultVOUtil.success("查询成功", search);
    }


    @RequestMapping(value = "/search/{page}/{size}" , method = RequestMethod.POST)
    public ResultVO pageQueary(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> pageSearch  = service.pageSearch(label, page, size);
        return ResultVOUtil.success("查询成功"
                , new PageResult<Label>(pageSearch.getTotalElements(), pageSearch.getContent()));
    }

}
