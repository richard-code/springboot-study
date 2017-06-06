package com.study.service.impl;

import com.study.dao.cluster.ICityDao;
import com.study.dao.master.IUserDao;
import com.study.entity.cluster.City;
import com.study.entity.master.User;
import com.study.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liqing on 2017/5/31 0031.
 */
@Service("demoService")
public class DemoServiceImpl implements IDemoService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ICityDao cityDao;

    @Transactional(value = "transactionManager")
    @Override
    public void addUserAndCity(String userName, String cityName) {
        User user = new User();
        user.setUserName(userName);
        user.setAge(20);
        user.setDescription(userName);
        userDao.insertUser(user);
        City city = new City();
        city.setCityName(cityName);
        city.setDescription(cityName);
        cityDao.insertCity(city);
    }
}
