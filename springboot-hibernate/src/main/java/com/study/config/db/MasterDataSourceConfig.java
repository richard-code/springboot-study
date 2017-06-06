package com.study.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by liqing on 2017/4/6 0006.
 */
@Configuration
@EnableJpaRepositories(basePackages = {MasterDataSourceConfig.REPOSITORY_PACKAGE},
        entityManagerFactoryRef = "masterEntityManagerFactory")
public class MasterDataSourceConfig extends AbstractDatasourceConfig {

    @Autowired
    private DBProperties dbProperties;

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String REPOSITORY_PACKAGE = "com.study.repository.master";
    static final String ENTITY_PACKAGE = "com.study.entity.master";

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

    /*@Primary JPA
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }*/

    @Primary
    @DependsOn({"transactionManager", "masterDataSource"})
    @Bean("masterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("masterDataSource")DataSource masterDataSource) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(masterDataSource);
        entityManager.setPackagesToScan(ENTITY_PACKAGE);
        entityManager.setPersistenceUnitName("masterDataSource");
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
