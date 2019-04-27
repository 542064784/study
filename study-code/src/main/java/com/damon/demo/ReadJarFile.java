package com.damon.demo;

import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;
/**
 * linux 读取classpath下文件
 *
 * @author Damon Chen
 * @date 2019/02/07
 */
public class ReadJarFile {

    public static void main(String[] args) throws IOException {
        /**
         *  jar包中的文件读取必须读成流的形式
         */
        ClassPathResource classPathResource = new ClassPathResource("a.txt");
        InputStream inputStream = classPathResource.getInputStream();
    }
}
