package com.study.dao.cluster;

import com.study.entity.cluster.City;
import org.apache.ibatis.annotations.Param;

/**
 * Created by liqing on 2017/5/15 0015.
 */
public interface ICityDao {

    City findByName(String cityName);

    void insertCity(City city);
}
