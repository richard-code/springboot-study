package com.study.dao.cluster.impl;

import com.study.dao.cluster.ICityDao;
import com.study.entity.cluster.City;
import com.study.entity.cluster.CityExample;
import com.study.mapper.cluster.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liqing on 2017/6/5 0005.
 */
@Repository
public class CityDaoImpl implements ICityDao {
    @Autowired
    private CityMapper cityMapper;

    @Override
    public City findByName(String cityName) {
        CityExample example = new CityExample();
        CityExample.Criteria criteria = example.createCriteria();
        criteria.andCityNameEqualTo(cityName);
        List<City> cities = cityMapper.selectByExample(example);
        return cities.get(0);
    }

    @Override
    public void insertCity(City city) {
        cityMapper.insert(city);
    }
}
