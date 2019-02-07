package com.hsbc.tt.camel.route;

import com.hsbc.tt.camel.bean.DynamicBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 动态路由实现动态to
 *
 * @author Damon Chen
 * @date 2018/11/23
 */
@Component
public class DispatcherRouteBuilder extends RouteBuilder {

    @Autowired
    private DynamicBean dynamicBean;

    @Override
    public void configure() throws Exception {
        /**
         * 可以使用@Handler使方法成为默认方法,不必在这边写方法名
         * dynamic 方式动态轮询指定方法,必须有判断可以返回null,否则会死循环
         */
        from("jms:queue").dynamicRouter(method(dynamicBean,"dynamicRouter"));

        /**
         * 可以使用@Handler使方法成为默认方法,不必在这边写方法名
         * recipientList 方式,可以返回多个to,exchange对象会复制多份
         */
        from("jms:queue").recipientList(method(dynamicBean,"recipientList"));

        /**
         * 获得ModelCamelContext 对象,设置当前exchange对象的id,再to到下一个节点.代码耦合性太高
         */
        from("jms:queue").process(exchange -> {
           getContext().getRouteDefinition(exchange.getExchangeId()).to("direct:a");
        });
        /**
         * 根据body或header中的属性判断to的具体值,不推荐
         */
        from("jms:queue").choice()
                .when(simple("${body.filed} equals a")).to("direct:a")
                .otherwise().to("direct:b");
    }
}
