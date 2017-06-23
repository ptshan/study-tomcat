package pers.qianshifengyi.tomcat.exercise8.http;

import pers.qianshifengyi.tomcat.util.Constants;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangshan on 17/5/26.
 */
public class HttpConnector8 implements Runnable{

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Constants.SERVER_PORT,1, InetAddress.getByName("127.0.0.1"));
            while(true){
                Socket socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                HttpProcessor8 httpProcessor8 = new HttpProcessor8(is,os);
                httpProcessor8.parseRequest8();
                httpProcessor8.parseResponse8();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public void start(){
        new Thread(this).start();
    }



}
