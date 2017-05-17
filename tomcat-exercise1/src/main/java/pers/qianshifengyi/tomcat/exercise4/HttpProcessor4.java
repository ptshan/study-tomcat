package pers.qianshifengyi.tomcat.exercise4;

import java.net.Socket;

/**
 * Created by zhangshan on 17/5/11.
 */
public class HttpProcessor4 {

    private Socket socket;

    private HttpProcessor4(){

    }



    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
