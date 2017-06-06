package com.study.repository.master;

import com.study.entity.master.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by liqing on 2017/6/5 0005.
 */
public interface UserRepository extends JpaSpecificationExecutor<User>,JpaRepository<User, Long> {

    User findByUserName(String userName);
}
