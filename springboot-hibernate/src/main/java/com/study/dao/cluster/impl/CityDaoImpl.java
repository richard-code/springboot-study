package com.study.dao.cluster.impl;

import com.study.dao.cluster.ICityDao;
import com.study.entity.cluster.City;
import com.study.repository.cluster.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by liqing on 2017/6/5 0005.
 */
@Repository
public class CityDaoImpl implements ICityDao {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public City findByCityName(String cityName) {
        return cityRepository.findByCityName(cityName);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }
}
