package pers.qianshifengyi.tomcat.exercise2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangshan on 17/5/2.
 */
public class HttpServer2 {

    public static void main(String[] args) {
        new HttpServer2().await();
    }

    public void await(){
        boolean shutdown = false;
        try {
            ServerSocket serverSocket = new ServerSocket(8081,1, InetAddress.getByName("127.0.0.1"));
            while(!shutdown){
                Socket socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                Request2 request2 = new Request2(is);
                request2.parse();

                Response2 response2 = new Response2(os);
                response2.setRequest(request2);

                String uri = request2.getUri();
                System.out.println("uri:"+uri);
                if(uri.equals(Constants.SHUTDOWN)){
                    shutdown = true;
                }else if(uri.startsWith("/servlet")){
                    ServletResourceProcessor2 srp = new ServletResourceProcessor2(request2,response2);
                    srp.process();
                }else{
                    StaticResourceProcessor2 srp = new StaticResourceProcessor2(request2,response2);
                    srp.process();
                }
                socket.close();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
