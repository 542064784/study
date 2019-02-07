package com.hsbc.tt.camel.route;

import com.hsbc.tt.camel.processor.FileReadProcessor;
import com.hsbc.tt.camel.processor.FileReadToCsvProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 路由读写文件
 *
 * @author Damon Chen
 * @date 2018/11/26
 */
@Component
public class FileRouteBuilder extends RouteBuilder {

    @Autowired
    private FileReadProcessor fileReadProcessor;
    @Autowired
    private FileReadToCsvProcessor fileReadToCsvProcessor;

    @Override
    public void configure() throws Exception {
        /**
         * 将c盘source目录下所有文件移动到c盘target目录下
         */
        from("file:c:\\source\\").to("file:c:\\target\\");
        /**
         * 将c盘source目录下a.txt文件移动到c盘target目录下文件名不变
         */
        from("file:c:\\source\\?fileName=a.txt").to("file:c:\\target\\?fileName=a.txt");
        /**
         * 读取c盘source目录下a.txt文件,获得inputStream对象进行操作,
         * 读取后文件默认被放在.camel文件夹下.
         * 可添加delete=true,读取后被删除;move=文件夹,读取后移动到某个文件夹,默认为.camel.
         */
        from("file:c:\\source\\?fileName=a.txt").process(fileReadProcessor);
        /**
         * 将csv文件默认按照","分割成List<List<String>>格式供process使用
         */
        from("file:c:\\source\\?fileName=a.csv").unmarshal().csv().process(fileReadToCsvProcessor);
    }
}
