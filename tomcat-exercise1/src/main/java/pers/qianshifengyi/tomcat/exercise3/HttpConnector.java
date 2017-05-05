package pers.qianshifengyi.tomcat.exercise3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangshan on 17/5/4.
 */
public class HttpConnector implements Runnable{

    private boolean stop = false;

    private String schema = "http";

    public String getSchema(){
        return schema;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8081,1, InetAddress.getByName("1270.0.1"));
            while(!stop){
                Socket socket = serverSocket.accept();
                HttpProcessor httpProcessor = new HttpProcessor(socket);
                httpProcessor.process();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void start(){
        new Thread(this).start();
    }

    public void stop(){
        stop = true;
    }

}
