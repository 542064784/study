package com.damon.repository;

import com.damon.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 数据库连接层repository接口
 *
 * @author Damon Chen
 * @date 2018/11/24
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {

    /**
     * 根据用户id查询用户
     *
     * @param userId 用户id
     * @return 查询到的用户
     */
    User findUserByUserId(Integer userId);
}