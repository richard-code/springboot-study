package com.study.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by liqing on 2017/4/7 0007.
 */
@Configuration
@EnableJpaRepositories(basePackages = {ClusterDataSourceConfig.REPOSITORY_PACKAGE},
        entityManagerFactoryRef = "clusterEntityManagerFactory")
public class ClusterDataSourceConfig extends AbstractDatasourceConfig {

    @Autowired
    private DBProperties dbProperties;

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String REPOSITORY_PACKAGE = "com.study.repository.cluster";
    static final String ENTITY_PACKAGE = "com.study.entity.cluster";

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

    /*@Bean JPA
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }*/

    @DependsOn({"transactionManager", "clusterDataSource"})
    @Bean("clusterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("clusterDataSource")DataSource clusterDataSource) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(clusterDataSource);
        entityManager.setPackagesToScan(ENTITY_PACKAGE);
        entityManager.setPersistenceUnitName("clusterDataSource");
        Properties properties = new Properties();
        properties.put("hibernate.jdbc.batch_size", 30);
        properties.put("hibernate.order_inserts", true);
        properties.put("hibernate.order_updates", true);
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        entityManager.setJpaProperties(properties);
        entityManager.setJpaVendorAdapter(jpaVendorAdapter());
        return entityManager;
    }

}
