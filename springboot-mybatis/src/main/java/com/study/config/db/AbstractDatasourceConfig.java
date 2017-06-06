package com.study.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * Created by liqing on 2017/4/10 0010.
 */
public class AbstractDatasourceConfig {

    @Autowired
    private DBProperties dbProperties;

    public DataSource getDruidDataSource(String clusterJDBCUrl, String clusterJDBCDriverClassName, String clusterJDBCUser, String clusterJDBCPassword) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(clusterJDBCUrl);
        dataSource.setDriverClassName(clusterJDBCDriverClassName);
        dataSource.setUsername(clusterJDBCUser);
        dataSource.setPassword(clusterJDBCPassword);

        dataSource.setInitialSize(dbProperties.initialize);
        dataSource.setMinIdle(dbProperties.minIdle);
        dataSource.setMaxActive(dbProperties.maxActive);
        dataSource.setMaxWait(dbProperties.maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(dbProperties.timeBetweenEvictionRunsMillis);

        dataSource.setTestWhileIdle(dbProperties.testWhileIdle);
        dataSource.setTestOnBorrow(dbProperties.testOnBorrow);
        dataSource.setTestOnReturn(dbProperties.testOnReturn);
        dataSource.setValidationQuery(dbProperties.validationQuery);

        dataSource.setPoolPreparedStatements(dbProperties.poolPreparedStatements);
        return dataSource;
    }

    public DataSource getXADruidDataSource(String jdbcUrl, String jdbcDriverClassName,
                                           String jdbcUser, String jdbcPassword,
                                           String uniqueName) {
        DruidXADataSource xaDataSource = new DruidXADataSource();
        xaDataSource.setUrl(jdbcUrl);
        xaDataSource.setDriverClassName(jdbcDriverClassName);
        xaDataSource.setUsername(jdbcUser);
        xaDataSource.setPassword(jdbcPassword);

        xaDataSource.setInitialSize(dbProperties.initialize);
        xaDataSource.setMinIdle(dbProperties.minIdle);
        xaDataSource.setMaxActive(dbProperties.maxActive);
        xaDataSource.setMaxWait(dbProperties.maxWait);
        xaDataSource.setTimeBetweenEvictionRunsMillis(dbProperties.timeBetweenEvictionRunsMillis);

        xaDataSource.setTestWhileIdle(dbProperties.testWhileIdle);
        xaDataSource.setTestOnBorrow(dbProperties.testOnBorrow);
        xaDataSource.setTestOnReturn(dbProperties.testOnReturn);
        xaDataSource.setValidationQuery(dbProperties.validationQuery);

        xaDataSource.setPoolPreparedStatements(dbProperties.poolPreparedStatements);

        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        dataSourceBean.setXaDataSource(xaDataSource);
        dataSourceBean.setUniqueResourceName(uniqueName);

        return dataSourceBean;
    }

}
