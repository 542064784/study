package com.damon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 * @author Damon Chen
 * @date 2018/11/25
 */
@RestController
@RequestMapping("/person")
@Configuration
public class PersonController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/call")
    public String getPoliceHelp(){
        return restTemplate.getForObject("http://Spring-Cloud-Eureka-Police/police/call",String.class);
    }

}
