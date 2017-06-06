package com.study.dao.master;

import com.study.entity.master.User;

import java.util.List;

/**
 * Created by liqing on 2017/5/15 0015.
 */
public interface IUserDao {

    User findByUserName(String userName);

    List<User> findByCondition(User user);

    void save(User user);
}
