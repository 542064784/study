package com.damon.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Damon Chen
 * @date 2018/11/25
 */
@RestController
public class ConfigClientController {

    @Value("${name}")
    private String name;

    @Value("${age}")
    private Integer age;

    @GetMapping("/get")
    public String information(){
        return "姓名是 : "+name+",年龄是 : "+age;
    }

}
