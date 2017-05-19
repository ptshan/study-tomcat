package pers.qianshifengyi.tomcat.exercise5.http;

import pers.qianshifengyi.tomcat.exercise1.Request1;
import pers.qianshifengyi.tomcat.exercise1.Response1;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangshan on 17/5/17.
 */
public class HttpProcessor5 {

    private Socket socket;

    private InputStream is;

    private OutputStream os;

    private HttpRequest5 request5 = new HttpRequest5();

    private HttpResponse5 httpResponse5 = new HttpResponse5();

    public HttpResponse5 getHttpResponse5() {
        return httpResponse5;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public HttpRequest5 getRequest5() {
        return request5;
    }

    public void parseRequest() throws Exception {
        is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        boolean firstLine = true;

        HttpServletRequest5 httpServletRequest = new HttpServletRequest5();
        request5.setServletRequest(httpServletRequest);
        System.out.println("------- enter parseRequest ");
        while(line != null && !line.trim().equals("")){
            if(firstLine == true){
                String[] firstArrs = line.split(" ");
                request5.setRequestURI(firstArrs[1]);
                firstLine = false;
            }else if(line.startsWith("content:")){ // 处理header
                break;
            }else if(!line.trim().equals("")){
                String[] headers = line.split(": ");
                httpServletRequest.addHeader(headers[0],headers[1]);
            }
            line = br.readLine();
        }

        System.out.println("httpServletRequest.getHeader(\"Host\"):"+httpServletRequest.getHeader("Host"));
        String[] hostString = httpServletRequest.getHeader("Host").split(":");
        httpServletRequest.setRemoteAddr(hostString[0]);

        System.out.println("parseRequest over");


    }

    public void parseResponse()throws Exception{
        os = socket.getOutputStream();
//        os.write("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: 22\r\n\r\n<h1>7777 6666 444</h1>".getBytes());
//        os.flush();
        HttpServletResponse5 httpServletResponse5 = new HttpServletResponse5();
        httpServletResponse5.setOutputStream(os);

        httpResponse5.setStream(os);
        httpResponse5.setServletResponse(httpServletResponse5);

        System.out.println("parseResponse over");
    }


}
