package com.study.dao.master.impl;

import com.study.dao.master.IUserDao;
import com.study.entity.master.User;
import com.study.entity.master.UserExample;
import com.study.mapper.master.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liqing on 2017/6/5 0005.
 */
@Repository
public class UserDaoImpl implements IUserDao {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String userName) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(example);
        return users.get(0);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userMapper.selectByExample(new UserExample());
        return users;
    }
}
