package com.tensuqare.base.service.impl;

import com.tensuqare.base.pjo.Label;
import com.tensuqare.base.repository.LabelRepository;
import com.tensuqare.base.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/19 11:51
 * @Description:
 */
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository repository;

    @Autowired
    private IdWorker idWorker;

    @Override
    public Label save(Label label) {
        label.setId(idWorker.nextId()+"");
        return repository.save(label);
    }

    @Override
    public Label update(Label label) {

        return repository.save(label);
    }

    @Override
    public Label findById(String labelId) {
        return repository.findById(labelId).get();
    }

    @Override
    public List<Label> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String labelId) {
        repository.deleteById(labelId);
    }

    @Override
    public List<Label> search(Label label) {
        return repository.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> list = new ArrayList();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate pr = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(pr);
                }

                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate state = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(state);
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates =  list.toArray(predicates);
                
                return criteriaBuilder.and(predicates);
            }
        });
    }

    @Override
    public Page<Label> pageSearch(Label label, int page, int size) {
        return repository.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate pr = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(pr);
                }

                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate state = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(state);
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates =  list.toArray(predicates);

                return criteriaBuilder.and(predicates);
            }
        },PageRequest.of(page - 1 , size));
    }


}
