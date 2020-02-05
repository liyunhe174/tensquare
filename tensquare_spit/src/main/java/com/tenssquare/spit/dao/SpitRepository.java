package com.tenssquare.spit.dao;

import com.tenssquare.spit.pjo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/22 12:19
 * @Description: 吐槽dao层
 */
public interface SpitRepository extends MongoRepository<Spit , String> {

    public Page<Spit> findByParentid(String parentid , Pageable pageable);
}
