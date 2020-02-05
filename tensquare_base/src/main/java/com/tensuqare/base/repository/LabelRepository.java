package com.tensuqare.base.repository;

import com.tensuqare.base.pjo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/19 11:41
 * @Description: label的dao
 */
public interface LabelRepository extends JpaRepository<Label , String> , JpaSpecificationExecutor<Label> {
}
