package com.hsbc.tt.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Damon Chen
 * @date 2018/11/26
 */
@Component
public class FileReadToCsvProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        List<List<String>> list = (List<List<String>>)exchange.getIn().getBody(List.class);
    }
}
