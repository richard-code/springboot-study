package com.study.config.db;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liqing on 2017/6/1 0001.
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DBProperties {
    public String masterUrl;
    public String masterDriverClassName;
    public String masterUser;
    public String masterPassword;
    public String masterUniqueName;

    public String clusterUrl;
    public String clusterDriverClassName;
    public String clusterUser;
    public String clusterPassword;
    public String clusterUniqueName;

    public Integer initialize;
    public Integer minIdle;
    public Integer maxActive;
    public Long maxWait;
    public Long timeBetweenEvictionRunsMillis;
    public Boolean testWhileIdle;
    public Boolean testOnBorrow;
    public Boolean testOnReturn;
    public String validationQuery;
    public Boolean poolPreparedStatements;

}
