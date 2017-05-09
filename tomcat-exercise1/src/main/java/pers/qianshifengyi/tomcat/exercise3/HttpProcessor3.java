package pers.qianshifengyi.tomcat.exercise3;

import pers.qianshifengyi.tomcat.exercise3.http.HttpRequest3;
import pers.qianshifengyi.tomcat.exercise3.http.HttpResponse3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zhangshan on 17/5/4.
 */
public class HttpProcessor3 {

    private Socket socket;

    private InputStream is;

    private OutputStream os;

    private HttpRequest3 request3;

    private HttpResponse3 response3;

    private HttpRequestLine requestLine = new HttpRequestLine();

    protected String method = null;
    protected String queryString = null;

    public HttpProcessor3(Socket socket){
        this.socket = socket;
    }

    public void process(){

        try{
            SocketInputStream sis = new SocketInputStream(socket.getInputStream(),2048);

            os = socket.getOutputStream();
            request3 = new HttpRequest3(sis);
            response3 = new HttpResponse3(os);
            response3.setRequest(request3);

            response3.setHeader("exeserver","it is mine");

            parseRequest(sis,os);
            parseHeaders(sis);

            if(request3.getRequestURI().startsWith("/servlet")){
                ServletProcessor3 processor3= new ServletProcessor3();
                processor3.process(request3,response3);
            }else{
                StaticProcessor3 processor3 = new StaticProcessor3();
                processor3.process(request3,response3);
            }

            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void parseRequest(SocketInputStream sis,OutputStream os){
        try {
            sis.readRequestLine(requestLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseHeaders(SocketInputStream sis){

    }

}
