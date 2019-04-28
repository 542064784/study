package com.damon.demo;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *  socket 客户端
 *
 * @author Damon Chen
 * @date {DATE}
 */
@Slf4j
public class SocketClient {

    public static void main(String[] args) throws IOException {
        // 连接地址为 localhost 端口为 9001 的socket服务
        Socket socket = new Socket("localhost",9001);
        // 获得输入流   用来服务端返回的消息
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 获得打印流   用来向服务端发送消息
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
        // 创建输入对象  从控制台输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("server call back message : ");
        while (true){
            String message = scanner.next();
            log.info(message);
            printWriter.println(message);
            printWriter.flush();
            // 当控制台输入 exit 时 结束
            if ("exit".equals(message)){
                break;
            }
            System.out.println(bufferedReader.readLine());
        }
        printWriter.close();
        bufferedReader.close();
        socket.close();

    }

}
