package com.damon.service.impl;

import com.damon.beans.User;
import com.damon.mapper.UserMapper;
import com.damon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 用户service层实现类
 *
 * @author Damon Chen
 * @date 2018/11/24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加新用户
     *
     * @param user 要添加的用户对象
     */
    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    /**
     * 根据id查询对象
     *
     * @param userId 用户id
     * @return 查询到的用户对象
     */
    @Override
    public User findByUserId(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     *  查询所有用户
     * @return 用户集合
     */
    @Override
    public List<User> findAll() {

        //将参数传给这个方法就可以实现物理分页了，非常简单。
        /*PageHelper.startPage(startSize, pageSize);
        List<User> userDomains = userMapper.selectList();
        PageInfo result = new PageInfo(userDomains);*/
        return userMapper.selectList();
    }

    /**
     * 更新用户数据
     *
     * @param user 用户对象
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 根据用户id删除用户
     *
     * @param userId 要删除用户的id
     */
    @Override
    public void deleteUserByUserId(Integer userId) {
        userMapper.deleteByPrimaryKey(userId);
    }
}
