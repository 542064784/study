package com.hsbc.tt.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Damon Chen
 * @date 2018/11/27
 */
@Component
public class QuartzRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        /**
         * 定时每一秒启动一次  可以使用cron表达式配置时间间隔
         */
        from("quartz2://quartzRouteBuilder?cron=*/0+*+*+*+*+?");
    }
}
