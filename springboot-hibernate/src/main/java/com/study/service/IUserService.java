package com.study.service;

import com.study.entity.master.User;
import com.study.model.AddParam;

import java.util.List;

/**
 * Created by liqing on 2017/5/15 0015.
 */
public interface IUserService {

    void addAll(AddParam addParam);

    User findUserByName(String userName);

	List<User> getAll();
}
