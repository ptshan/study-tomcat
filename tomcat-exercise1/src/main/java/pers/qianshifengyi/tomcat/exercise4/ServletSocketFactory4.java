package pers.qianshifengyi.tomcat.exercise4;

import pers.qianshifengyi.tomcat.util.Constants;
import pers.qianshifengyi.tomcat.util.StringManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zhangshan on 17/5/15.
 */
public class ServletSocketFactory4 {

    private static StringManager sm=  StringManager.getStringManager("pers.qianshifengyi.tomcat.exercise4");

    private static ServerSocket serverSocket= null;

    private synchronized static ServerSocket getServerSocket() throws IOException {
        if(serverSocket == null) {
            serverSocket = new ServerSocket(Constants.SERVER_PORT, 1, InetAddress.getByName(Constants.SERVER_IP));
        }
        return serverSocket;
    }

    public static Socket getSocket(){
        Socket socket = null;
        try {
            socket = getServerSocket().accept();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(sm.getString("servletSocketFactory4.getSocketError"));
        }
        return socket;
    }



}
