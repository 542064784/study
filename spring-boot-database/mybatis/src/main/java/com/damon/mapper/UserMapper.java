package com.damon.mapper;

import com.damon.beans.User;
import java.util.List;

/**
 * 数据库连接层mapper接口
 *
 * @author Damon Chen
 * @date 2018/11/24
 */
public interface UserMapper {

    /**
     * 添加新用户到数据库
     *
     * @param user 要添加的用户对象
     */
    void insert(User user);

    /**
     * 查询所有用户对象
     *
     * @return 用户列表
     */
    List<User> selectList();

    /**
     * 根据id查询用户
     *
     * @param userId 要查询的用户id
     * @return 查询到的用户对象
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 更新用户信息
     *
     * @param user 要更新的用户的数据
     */
    void updateUser(User user);

    /**
     * 根据用户id删除用户
     *
     * @param userId 要删除用户的id
     */
    void deleteByPrimaryKey(Integer userId);

}