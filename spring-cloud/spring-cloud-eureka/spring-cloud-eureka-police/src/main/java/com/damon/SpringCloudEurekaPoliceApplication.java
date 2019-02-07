package com.damon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * @author Damon Chen
 * @date 2018/11/25
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringCloudEurekaPoliceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaPoliceApplication.class, args);
    }
}
