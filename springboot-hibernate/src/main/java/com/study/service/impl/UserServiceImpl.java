package com.study.service.impl;

import com.study.dao.master.IUserDao;
import com.study.entity.master.User;
import com.study.model.AddParam;
import com.study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liqing on 2017/5/15 0015.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao IUserDao;
    @Override
    public void addAll(AddParam addParam) {
    }

    @Override
    public User findUserByName(String userName) {
        return IUserDao.findByUserName(userName);
    }
}
