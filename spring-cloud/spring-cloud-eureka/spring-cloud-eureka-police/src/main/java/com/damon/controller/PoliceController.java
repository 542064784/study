package com.damon.controller;

import com.damon.entity.Police;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Damon Chen
 * @date 2018/11/25
 */
@RestController
@RequestMapping("/police")
public class PoliceController {

    @GetMapping("/call")
    public Police getPolice(){
        Police police = new Police();
        police.setId(1);
        police.setName("东方");
        return police;
    }

}
