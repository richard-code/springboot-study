package com.study.config.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by liqing on 2017/4/7 0007.
 */
@Slf4j
@Configuration
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig extends AbstractDatasourceConfig {

    @Autowired
    private DBProperties dbProperties;

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.study.mapper.cluster";
    static String MYBATIS_CONFIG = "mybatis/mybatis-cluster-config.xml";
    static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";
    static final String MAPPER_ENTIRY = "com.study.entity.cluster";

    /*@Bean(name = "clusterDataSource")
    public DataSource clusterDataSource(){
        return getDruidDataSource(clusterJDBCUrl, clusterJDBCDriverClassName, clusterJDBCUser, clusterJDBCPassword);
    }*/

    @Bean(name = "clusterDataSource")
    public DataSource clusterDataSource(){
        return getXADruidDataSource(dbProperties.clusterUrl, dbProperties.clusterDriverClassName,
                dbProperties.clusterUser, dbProperties.clusterPassword,
                dbProperties.clusterUniqueName);
    }

    /*@Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(){
        return new DataSourceTransactionManager(clusterDataSource());
    }*/

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("clusterDataSource")DataSource clusterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        sessionFactory.setTypeAliasesPackage(MAPPER_ENTIRY);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
