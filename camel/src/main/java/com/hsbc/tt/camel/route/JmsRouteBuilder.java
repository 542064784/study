package com.hsbc.tt.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsMessage;
import org.springframework.stereotype.Component;

/**
 * @author Damon Chen
 * @date 2018/11/27
 */
@Component
public class JmsRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        /**
         * jms 使用camel  jms:queue名称   在配置文件application.yml中配置mq连接参数
         * 如果没有自定义mqConnectFactory ibm mq的优先级高于其他mq.
         * 有自定义mqConnectFactory 使用自定义的连接工场
         */
        from("jms:queueName").to("jms:queueName");

        /**
         * 使用这种方式可以做到camel整合jms的手动答复功能
         */
        from("jms:queueName?acknowledgementModeName=CLIENT_ACKNOWLEDGE").process(exchange -> {
            JmsMessage jmsMessage = exchange.getIn(JmsMessage.class);
            jmsMessage.getJmsMessage().acknowledge();
        });
    }
}
