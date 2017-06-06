package com.study.dao.master.impl;

import com.google.common.collect.Lists;
import com.study.dao.master.IUserDao;
import com.study.entity.master.User;
import com.study.repository.master.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by liqing on 2017/6/5 0005.
 */
@Repository
public class UserDaoImpl implements IUserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> findByCondition(User user) {
        Specification<User> spec = (root, query, cb) -> {
            List<Predicate> ps = Lists.newArrayList();
            if (StringUtils.isNotBlank(user.getUserName())){
                ps.add(cb.equal(root.get("userName").as(String.class), user.getUserName()));
            }
            if (user.getAge() != null){
                ps.add(cb.equal(root.get("age").as(Integer.class), user.getAge()));
            }
            return cb.and(ps.toArray(new Predicate[ps.size()]));
        };
        return userRepository.findAll(spec);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
