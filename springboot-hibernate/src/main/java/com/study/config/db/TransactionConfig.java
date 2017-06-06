package com.study.config.db;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.annotation.Resource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * Created by liqing on 2017/6/1 0001.
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(120);
        return userTransactionImp;
    }

    @Bean(name = "userTransactionManager", initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @DependsOn({"userTransaction", "userTransactionManager"})
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("userTransaction")UserTransaction userTransaction,
                                                         @Qualifier("userTransactionManager")TransactionManager userTransactionManager) throws Throwable {
        AtomikosJtaPlatform.transaction = userTransaction;
        AtomikosJtaPlatform.transactionManager = userTransactionManager;
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
}
