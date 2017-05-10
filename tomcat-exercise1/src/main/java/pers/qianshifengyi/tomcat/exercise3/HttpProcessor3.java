package pers.qianshifengyi.tomcat.exercise3;

import pers.qianshifengyi.tomcat.exercise3.http.HttpRequest3;
import pers.qianshifengyi.tomcat.exercise3.http.HttpResponse3;
import pers.qianshifengyi.tomcat.util.RequestUtil;
import pers.qianshifengyi.tomcat.util.StringManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zhangshan on 17/5/4.
 */
public class HttpProcessor3 {

    private StringManager sm = StringManager.getStringManager("pers.qianshifengyi.tomcat.exercise3");

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


    public void parseRequest(SocketInputStream sis,OutputStream os)throws IOException,ServletException{
        sis.readRequestLine(requestLine);
        String method = new String(requestLine.method,0,requestLine.methodEnd);
        String protocol = new String(requestLine.protocol,0,requestLine.protocolEnd);
        String uri = null;
        if(method.length()<1){
            throw new ServletException(sm.getString("missing.http.request.method"));
        }else if(requestLine.uriEnd < 1){
            throw new ServletException(sm.getString("missing.http.request.uri"));
        }

        String question = new String(requestLine.uri,0,requestLine.uriEnd);
        String queryStr = null;
        if(question.contains("?")){
            queryStr = question.substring(question.indexOf('?'),question.length());
            uri = question.substring(0,question.indexOf('?'));
        }else{
            uri = question;
        }
        request3.setQueryString(queryStr);

        // http://www.baidu.com/
        if(!uri.startsWith("/")){
            int pos = uri.indexOf("://");
            if(pos != -1){
                pos = uri.indexOf('/',pos+3);
                if(pos != -1){
                    uri = uri.substring(pos);
                }else{
                    uri = "";
                }
            }
        }

        uri="/servlet/StaticResources;jsessionid=D4FB78B86CFA97ECAFB6A035E473295B;aabb";

        // 处理uri后的jsessionid  ";jsessionid="
        // /servlet/StaticResources;jsessionid=D4FB78B86CFA97ECAFB6A035E473295B 处理后 /servlet/StaticResources
        // /servlet/StaticResources;jsessionid=D4FB78B86CFA97ECAFB6A035E473295B;aabb 处理后 /servlet/StaticResources;aabb
        String jsessionidStr = ";jsessionid=";
        int semicolon = uri.indexOf(jsessionidStr);
        if(semicolon > 0){
            String rest= uri.substring(semicolon);
        }







    }

    public void parseHeaders(SocketInputStream sis)throws IOException,ServletException{
        while(true) {
            HttpHeader header = new HttpHeader();
            sis.readHeader(header);
            if(header.nameEnd == 0){
                if(header.valueEnd == 0){
                    return;
                }else{
                    throw new ServletException(sm.getString("httpProcessor3.parseHeaders.colon"));
                }
            }

            String name = new String(header.name,0,header.nameEnd);
            String value = new String(header.value,0,header.valueEnd);
            request3.addHeader(name,value);


            if(name.equals("Cookie")){
                Cookie[] cookies = RequestUtil.parseCookieHeader(value);
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("jsessionid")){
                        // 只接收第一次的sessionId
                        if(!request3.isRequestedSessionIdFromCookie()){

                            request3.setRequestedSessionId(cookie.getValue());
                            request3.setRequestedSessionIdFromCookie(true);
                            request3.setRequestedSessionURL(false);
                        }
                    }
                    request3.addCookie(cookie);
                }
            }else if(name.equals("Content-length")){
                int clength = -1;
                try{
                    clength = Integer.parseInt(value);
                }catch (NumberFormatException e){
                    throw new ServletException(sm.getString("httpProcessor3.parseHeaders.contentLength"));
                }
                request3.setContentLength(clength);
            }else if(name.equals("Content-type")){
                request3.setContentType(value);
            }
        }
    }

    public static void main(String[] args) {
        String uri="/servlet/StaticResources;jsessionid=D4FB78B86CFA97ECAFB6A035E473295B;aabb";

        // 处理uri后的jsessionid  ";jsessionid="
        // /servlet/StaticResources;jsessionid=D4FB78B86CFA97ECAFB6A035E473295B 处理后 /servlet/StaticResources
        // /servlet/StaticResources;jsessionid=D4FB78B86CFA97ECAFB6A035E473295B;aabb 处理后 /servlet/StaticResources;aabb
        String jsessionidStr = ";jsessionid=";
        int semicolon = uri.indexOf(jsessionidStr);
        if(semicolon > 0){
            String rest= uri.substring(semicolon+jsessionidStr.length());
            int semicolon2 = rest.indexOf(";");
            String jsessionId = null;
            if(semicolon2 > 0){
                //String jsessionId =


            }else{

            }
            System.out.println(rest);
        }

    }

}
