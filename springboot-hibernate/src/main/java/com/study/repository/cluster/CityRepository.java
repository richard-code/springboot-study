package com.study.repository.cluster;

import com.study.entity.cluster.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liqing on 2017/6/5 0005.
 */
public interface CityRepository extends JpaRepository<City, Long> {

    City findByCityName(String cityName);
}
