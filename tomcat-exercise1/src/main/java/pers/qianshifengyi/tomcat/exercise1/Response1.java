package pers.qianshifengyi.tomcat.exercise1;

import java.io.OutputStream;

/**
 * Created by zhangshan193 on 17/4/28.
 */
public class Response1 {

    private Request1 request1;
    private OutputStream os;

    public Response1(Request1 request1,OutputStream os){
        this.request1 = request1;
        this.os = os;
    }

    public void sendStaticResource(){

    }


}
