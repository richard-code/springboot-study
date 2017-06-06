package com.study.dao.master;

import com.study.entity.master.User;

import java.util.List;

/**
 * Created by liqing on 2017/5/15 0015.
 */
public interface IUserDao {
    User findByName(String userName);

    void insertUser(User user);

	List<User> getAll();
}
