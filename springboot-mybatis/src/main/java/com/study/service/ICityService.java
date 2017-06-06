package com.study.service;

import com.study.entity.cluster.City;

/**
 * Created by liqing on 2017/5/31 0031.
 */
public interface ICityService {

    City findCityByName(String cityName);
}
