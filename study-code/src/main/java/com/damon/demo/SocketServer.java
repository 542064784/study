package com.damon.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *  socket server
 *
 * @author Damon Chen
 * @date {DATE}
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        // 创建接收端口为9001 的socket服务
        ServerSocket serverSocket = new ServerSocket(9001);
        // 等待客户端连接
        Socket accept = serverSocket.accept();

        // 获得输入流   用来接收客户端的消息
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        // 获得打印流   用来向客户端输出消息
        PrintWriter printWriter = new PrintWriter(accept.getOutputStream(),true);
        System.out.println("get message from client is :");
        while (true){
            String message = bufferedReader.readLine();
            //  如果消息是 exit  则结束通信
            if ("exit".equals(message)){
                break;
            }
            System.out.println(message);
            printWriter.println(message.toUpperCase());
        }
        printWriter.close();
        bufferedReader.close();
        accept.close();
        serverSocket.close();
    }
}
