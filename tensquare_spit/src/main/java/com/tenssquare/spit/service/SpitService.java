package com.tenssquare.spit.service;

import com.tenssquare.spit.dao.SpitRepository;
import com.tenssquare.spit.pjo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/22 12:21
 * @Description: 吐槽Service层
 */
@Service
public class SpitService {

    @Autowired
    private SpitRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;
    /**
     * 描述:  吐槽的添加方法
     * @param spit:   吐槽的实体类
     * @return com.tenssquare.spit.pjo.Spit
     * @author  liyunhe
     * @Datetime  2020/1/22 16:10
     */
    public Spit save(Spit spit) {
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setVisits(0);//浏览量         
        spit.setShare(0);//分享数         
        spit.setThumbup(0);//点赞数         
        spit.setComment(0);//回复数         
        spit.setState("1");//状态
        //将上一级的评论加一
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));

            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        return repository.save(spit);

    }

    /**
     * 描述: 查询所有的吐槽信息
     * @return java.util.List<com.tenssquare.spit.pjo.Spit>
     * @author  liyunhe
     * @Datetime  2020/1/22 16:11
     */
    public List<Spit> findAll() {
        return repository.findAll();
    }



    /**
     * 描述: 查询单条吐槽信息
     * @param id:   吐槽的主键
     * @return com.tenssquare.spit.pjo.Spit
     * @author  liyunhe
     * @Datetime  2020/1/22 16:11
     */
    public Spit findOne(String id) {
        return repository.findById(id).get();
    }


    /**
     * 描述:
     * @param spit:  修改吐槽
     * @return com.tenssquare.spit.pjo.Spit
     * @author  liyunhe
     * @Datetime  2020/1/22 16:13
     */
    public Spit update(Spit spit) {
        return repository.save(spit);
    }

    /**
     * 描述: 根据主键删除吐槽
     * @param id:
     * @return void
     * @author  liyunhe
     * @Datetime  2020/1/22 16:13
     */
    public void delete(String id) {
        repository.deleteById(id);
    }


    //TODO  分页未写完
    public List<Spit> search(Spit spit, int page, int size) {

        Example<Spit> spitExample = Example.of(spit);
        return  null;
    }



    public Page<Spit> findByParentid(String parentid, int page, int size) {
        PageRequest of = PageRequest.of(page - 1, size);
        return repository.findByParentid(parentid, of);
    }


    public void updateThumup(String spitId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));

        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }

}
