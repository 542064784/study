package com.damon.contioller;

import com.damon.beans.Result;
import com.damon.beans.User;
import com.damon.exception.BusinessException;
import com.damon.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 用户controller层
 *
 * @author Damon Chen
 * @date 2018/11/24
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加客户
     *
     * @param user 新客户信息
     * @return 结果集
     */
    @PostMapping("/saveUser")
    public Result saveUser(User user){
        Result result;
        try {
            userService.save(user);
            log.info("保存客户信息到数据库成功 . . . . . ");
            result = new Result(200,"保存客户信息成功.");
        }catch (BusinessException e){
            log.error(e.getMessage());
            result = new Result(500,e.getMessage());
        }
        return result;
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param userId 用户id
     * @return 查询到的用户信息
     */
    @GetMapping("/findUserByUserId")
    public User findUserByUserId(Integer userId){
        return userService.findByUserId(userId);
    }

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @GetMapping("/findAllUser")
    public List<User> findAllUser(){
        return userService.findAll();
    }

    /**
     * 更新用户信息
     *
     * @param user 要更新的用户对象
     * @return 结果集
     */
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        Result result;
        try {
            userService.updateUser(user);
            log.info("更新客户信息到数据库成功,被更新的客户id是: " +user.getUserId());
            result = new Result(200,"更新客户信息成功.");
        }catch (BusinessException e){
            log.error(e.getMessage());
            result = new Result(500,e.getMessage());
        }
        return result;
    }

    /**
     * 根据用户id删除用户
     *
     * @param userId 要删除用户的is
     * @return 结果集
     */
    @DeleteMapping("/deleteUserByUserId/{userId}")
    public Result deleteUserByUserId(@PathVariable Integer userId){
        Result result;
        try {
            userService.deleteUserByUserId(userId);
            log.info("删除客户成功,被删除的客户id是 : "+userId);
            result = new Result(200,"删除客户成功.");
        }catch (BusinessException e){
            log.error(e.getMessage());
            result = new Result(500,e.getMessage());
        }
        return result;
    }

}
