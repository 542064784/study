package com.damon.service.impl;

import com.damon.beans.User;
import com.damon.repository.UserRepository;
import com.damon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
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
    private UserRepository userRepository;

    /**
     * 添加新用户或更新用户信息
     *
     * @param user 要添加的用户对象或要更新的用户信息
     */
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * 根据id查询对象
     *
     * @param userId 用户id
     * @return 查询到的用户对象
     */
    @Override
    public User findByUserId(Integer userId) {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        Sort sort = new Sort(Sort.Direction.ASC, "filed");
        PageRequest pageRequest = PageRequest.of(0, 1,sort);
        userRepository.findAll(pageRequest);
        userRepository.findAll(sort);
        return userRepository.findUserByUserId(userId);
    }

    /**
     *  查询所有用户
     * @return 用户集合
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 根据用户id删除用户
     *
     * @param userId 要删除用户的id
     */
    @Override
    public void deleteUserByUserId(Integer userId) {
        userRepository.deleteById(userId);
    }
}
