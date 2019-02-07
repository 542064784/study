package com.damon.service.impl;

import com.damon.BaseTest;
import com.damon.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * UserService 测试类
 *
 * @author Damon Chen
 * @date 2018/11/24
 */
@RunWith(SpringRunner.class)
public class UserServiceImplTest extends BaseTest {

    @InjectMocks
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save() {
        when(userRepository.save(getUser())).thenReturn(getUser());
        userService.save(getUser());
    }

    @Test
    public void findByUserId() {
        when(userRepository.findUserByUserId(1)).thenReturn(getUser());
        assertEquals(getUser(),userService.findByUserId(1));
    }

    @Test
    public void findAll() {
        when(userRepository.findAll()).thenReturn(getUserList());
        assertEquals(getUser(),userService.findAll().get(0));
    }

    @Test
    public void updateUser() {
        when(userRepository.save(getUser())).thenReturn(getUser());
        userService.save(getUser());
    }

    @Test
    public void deleteUserByUserId() {
        doNothing().when(userRepository).deleteById(1);
        userService.deleteUserByUserId(1);
    }
}