package pers.qianshifengyi.tomcat.exercise6.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zhangshan on 17/6/17.
 */
public class HttpProcessor6 {

    private Socket socket;

    private InputStream is;

    private OutputStream os;

    private HttpRequest6 request6 = new HttpRequest6();

    private HttpResponse6 httpResponse6 = new HttpResponse6();

    public HttpResponse6 getHttpResponse6() {
        return httpResponse6;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public HttpRequest6 getRequest6() {
        return request6;
    }

    /**
     * 请求:http://127.0.0.1:8082/test/TestServlet
     * 请求:http://127.0.0.1:8082/test/TestServlet2
     * @throws Exception
     */
    public void parseRequest() throws Exception {
        is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        boolean firstLine = true;

        HttpServletRequest6 httpServletRequest = new HttpServletRequest6();
        request6.setServletRequest(httpServletRequest);
        while(line != null && !line.trim().equals("")){
            if(firstLine == true){
                String[] firstArrs = line.split(" ");
                request6.setRequestURI(firstArrs[1]);
                request6.setDecodedRequestURI(firstArrs[1]);
                request6.setContextPath("/test");
                httpServletRequest.setProtocol("http");
                httpServletRequest.setContextPath("/test");
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
        HttpServletResponse6 httpServletResponse6 = new HttpServletResponse6();
        httpServletResponse6.setOutputStream(os);

        httpResponse6.setStream(os);
        httpResponse6.setServletResponse(httpServletResponse6);

        System.out.println("parseResponse over");
    }


}
