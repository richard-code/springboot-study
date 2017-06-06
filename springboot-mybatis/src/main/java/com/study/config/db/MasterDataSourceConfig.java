package com.study.config.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by liqing on 2017/4/6 0006.
 */
@Slf4j
@Configuration
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig extends AbstractDatasourceConfig {

    @Autowired
    private DBProperties dbProperties;

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.study.mapper.master";
    static String MYBATIS_CONFIG = "mybatis/mybatis-master-config.xml";
    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";
    static final String MAPPER_ENTIRY = "com.study.entity.master";

    /*@Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource(){
        return getDruidDataSource(masterJDBCUrl, masterJDBCDriverClassName, masterJDBCUser, masterJDBCPassword);
    }*/

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource(){
        return getXADruidDataSource(dbProperties.masterUrl, dbProperties.masterDriverClassName,
                dbProperties.masterUser, dbProperties.masterPassword,
                dbProperties.masterUniqueName);
    }

    /*@Primary
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(){
        return new DataSourceTransactionManager(masterDataSource());
    }*/

    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource")DataSource masterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        sessionFactory.setTypeAliasesPackage(MAPPER_ENTIRY);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
