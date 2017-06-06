package com.study.dao.cluster;

import com.study.entity.cluster.City;

/**
 * Created by liqing on 2017/5/15 0015.
 */
public interface ICityDao {

    City findByCityName(String cityName);

    void save(City city);
}
