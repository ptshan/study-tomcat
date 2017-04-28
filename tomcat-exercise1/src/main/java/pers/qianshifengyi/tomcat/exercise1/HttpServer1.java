package pers.qianshifengyi.tomcat.exercise1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangshan193 on 17/4/28.
 */
public class HttpServer1 {

    public static void main(String[] args) {

    }

    public void await(){
        boolean shutdown = true;
        try {
            ServerSocket serverSocket = new ServerSocket(8081,1,InetAddress.getByName("127.0.0.1"));
            while(shutdown) {
                Socket socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                Request1 request1 = new Request1(is);
                request1.parse();

                Response1 response1 = new Response1(request1, os);
                response1.sendStaticResource();

                socket.close();
                shutdown = request1.getUri().equals("SHUTDOWN");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
