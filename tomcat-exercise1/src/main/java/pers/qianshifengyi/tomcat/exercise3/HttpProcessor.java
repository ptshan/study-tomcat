package pers.qianshifengyi.tomcat.exercise3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zhangshan on 17/5/4.
 */
public class HttpProcessor {

    private Socket socket;

    private InputStream is;

    private OutputStream os;

    public HttpProcessor(Socket socket){
        this.socket = socket;
    }

    public void process(){
        try{
            is = socket.getInputStream();
            os = socket.getOutputStream();




        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
