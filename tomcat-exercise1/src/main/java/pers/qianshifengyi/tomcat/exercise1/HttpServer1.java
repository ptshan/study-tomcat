package pers.qianshifengyi.tomcat.exercise1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangshan on 17/4/28.
 */
public class HttpServer1 {

    public static final String SHUTDOWN = "/SHUTDOWN" ;
    public static final String WEBAPPS = "/Users/zhangshan193/Documents/projects/git_project/study-tomcat/tomcat-exercise1/src/main/webapps" ;

    public static void main(String[] args) {

        new HttpServer1().await();

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
                System.out.println("uri:"+request1.getUri());


                System.out.println("socket.isClosed():"+socket.isClosed());
                Response1 response1 = new Response1(os);
                response1.setRequest1(request1);
                response1.sendStaticResource();

                socket.close();
                shutdown = !request1.getUri().equals(SHUTDOWN);
                System.out.println("shutdown:"+shutdown);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
