package com.hsbc.tt.camel.bean;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
/**
 * 动态路由bean
 *
 * @author Damon Chen
 * @date 2018/11/23
 */
@Component
public class DynamicBean {

    private static final String DISPATCHER_DIRECT = "direct:";

    /**
     * dynamic方式实现动态路由 必须设置使返回为null,否则会死循环
     *
     * @param exchange
     * @return
     */
    public String dynamicRouter(Exchange exchange) {
        Object splitter = exchange.getIn().getHeader("splitter");
        if (null == splitter) {
            exchange.getIn().setHeader("splitter", true);
            return DISPATCHER_DIRECT + "a";
        }
        return null;
    }

    /**
     * recipientList方式实现动态路由,可以String[],exchange对象会复制多份.
     *
     * @param exchange
     * @return
     */
    public String recipientList(Exchange exchange){
        return DISPATCHER_DIRECT + "a";
    }

}
