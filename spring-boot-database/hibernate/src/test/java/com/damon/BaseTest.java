package com.damon;

import com.damon.beans.User;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Damon Chen
 * @date 2018/11/24
 */
public class BaseTest {

    public User getUser(){
        User user = new User();
        user.setUserId(1);
        user.setUserName("aaaaaaaa");
        user.setPassword("bbbbbbbb");
        user.setPhone("12345678901");
        return user;
    }

    public List<User> getUserList(){
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setUserId(1);
        user1.setUserName("aaaaaaaa");
        user1.setPassword("bbbbbbbb");
        user1.setPhone("12345678901");
        User user2 = new User();
        user2.setUserId(1);
        user2.setUserName("aaaaaaaa");
        user2.setPassword("bbbbbbbb");
        user2.setPhone("12345678901");
        User user3 = new User();
        user3.setUserId(1);
        user3.setUserName("aaaaaaaa");
        user3.setPassword("bbbbbbbb");
        user3.setPhone("12345678901");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }

}
