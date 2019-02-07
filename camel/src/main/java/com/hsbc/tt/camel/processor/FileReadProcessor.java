package com.hsbc.tt.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 读取文件
 *
 * @author Damon Chen
 * @date 2018/11/26
 */
@Component
public class FileReadProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        InputStream inputStream = exchange.getIn().getBody(InputStream.class);
    }
}
