package com.study.service.impl;

import com.study.dao.cluster.ICityDao;
import com.study.dao.cluster.impl.CityDaoImpl;
import com.study.entity.cluster.City;
import com.study.mapper.cluster.CityMapper;
import com.study.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liqing on 2017/5/31 0031.
 */
@Service("cityService")
public class CityServiceImpl implements ICityService {
    @Autowired
    private ICityDao cityDao;

    @Override
    public City findCityByName(String cityName) {
        return cityDao.findByName(cityName);
    }
}
