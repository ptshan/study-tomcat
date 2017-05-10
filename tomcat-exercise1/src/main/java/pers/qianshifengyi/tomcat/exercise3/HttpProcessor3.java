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
            String rest= uri.substring(semicolon+jsessionidStr.length());
            int semicolon2 = rest.indexOf(";");
            String jsessionId = null;
            if(semicolon2 > 0){
                jsessionId = rest.substring(0,semicolon2);
                rest = rest.substring(semicolon2);
                System.out.println("jsessionId:"+jsessionId+",rest:"+rest);

            }else{
                jsessionId=rest;
                rest="";
                System.out.println("jsessionId:"+jsessionId);
            }
            request3.setRequestedSessionId(jsessionId);
            request3.setRequestedSessionURL(true);
            uri=uri.substring(0,semicolon)+rest;
            System.out.println("uri:"+uri);
        }else{
            request3.setRequestedSessionId(null);
            request3.setRequestedSessionURL(true);
        }






// Normalize URI (using String operations at the moment)
        String normalizedUri = normalize(uri);

        // Set the corresponding request properties
        request3.setMethod(method);
        request3.setProtocol(protocol);
        if (normalizedUri != null) {
             request3.setRequestURI(normalizedUri);
        }
        else {
            request3.setRequestURI(uri);
        }

        if (normalizedUri == null) {
            throw new ServletException("Invalid URI: " + uri + "'");
        }
    }

    /**
     * Return a context-relative path, beginning with a "/", that represents
     * the canonical version of the specified path after ".." and "." elements
     * are resolved out.  If the specified path attempts to go outside the
     * boundaries of the current context (i.e. too many ".." path elements
     * are present), return <code>null</code> instead.
     *
     * @param path Path to be normalized
     */
    protected String normalize(String path) {
        if (path == null)
            return null;
        // Create a place for the normalized path
        String normalized = path;

        // Normalize "/%7E" and "/%7e" at the beginning to "/~"
        if (normalized.startsWith("/%7E") || normalized.startsWith("/%7e"))
            normalized = "/~" + normalized.substring(4);

        // Prevent encoding '%', '/', '.' and '\', which are special reserved
        // characters
        if ((normalized.indexOf("%25") >= 0)
                || (normalized.indexOf("%2F") >= 0)
                || (normalized.indexOf("%2E") >= 0)
                || (normalized.indexOf("%5C") >= 0)
                || (normalized.indexOf("%2f") >= 0)
                || (normalized.indexOf("%2e") >= 0)
                || (normalized.indexOf("%5c") >= 0)) {
            return null;
        }

        if (normalized.equals("/."))
            return "/";

        // Normalize the slashes and add leading slash if necessary
        if (normalized.indexOf('\\') >= 0)
            normalized = normalized.replace('\\', '/');
        if (!normalized.startsWith("/"))
            normalized = "/" + normalized;

        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0)
                break;
            if (index == 0)
                return (null);  // Trying to go outside our context
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) +
                    normalized.substring(index + 3);
        }

        // Declare occurrences of "/..." (three or more dots) to be invalid
        // (on some Windows platforms this walks the directory tree!!!)
        if (normalized.indexOf("/...") >= 0)
            return (null);

        // Return the normalized path that we have completed
        return (normalized);

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

    }

}
