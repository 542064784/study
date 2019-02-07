package com.damon.service;

import com.damon.beans.User;
import java.util.List;

/**
 * 用户service层接口
 *
 * @author Damon Chen
 * @date 2018/11/24
 */
public interface UserService {

    /**
     * 添加新用户或更新用户信息
     *
     * @param user 要添加的用户对象或要更新的用户
     */
    void save(User user);

    /**
     * 根据用户id查找用户
     *
     * @param userId 用户id
     * @return 找到的用户对象
     */
    User findByUserId(Integer userId);

    /**
     * 查找所有用户
     *
     * @return 用户集合
     */
    List<User> findAll();

    /**
     * 根据用户id删除用户
     *
     * @param userId 要删除用户的id
     */
    void deleteUserByUserId(Integer userId);
}
