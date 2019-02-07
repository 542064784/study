package com.damon.contioller;

import com.damon.BaseTest;
import com.damon.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * UserController 测试类
 *
 * @author Damon Chen
 * @date 2018/11/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest extends BaseTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void saveUser() throws Exception {
        doNothing().when(userService).save(getUser());
        mockMvc.perform(post("/user/saveUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getUser())))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserByUserId() throws Exception {
        when(userService.findByUserId(1)).thenReturn(getUser());
        mockMvc.perform(get("/user/findUserByUserId?userId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUser() throws Exception {
        when(userService.findAll()).thenReturn(getUserList());
        mockMvc.perform(get("/user/findAllUser")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        doNothing().when(userService).save(getUser());
        mockMvc.perform(put("/user/updateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getUser())))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserByUserId() throws Exception {
        doNothing().when(userService).save(getUser());
        mockMvc.perform(delete("/user/deleteUserByUserId/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}