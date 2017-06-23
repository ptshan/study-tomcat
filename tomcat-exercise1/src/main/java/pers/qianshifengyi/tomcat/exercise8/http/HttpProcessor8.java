package pers.qianshifengyi.tomcat.exercise8.http;

import org.apache.catalina.HttpRequest;
import org.apache.catalina.HttpResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by zhangshan on 17/5/26.
 */
public class HttpProcessor8 {

    private InputStream is;

    private OutputStream os;

    private HttpServletResponse8 httpServletResponse8;

    private HttpServletRequest8 httpServletRequest8;

    private HttpRequest8 httpRequest8;

    private HttpResponse8 httpResponse8;

    public HttpProcessor8(InputStream is, OutputStream os){

    }

    public void parseRequest8()throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        boolean firstLine = true;
        while(line != null ){
            if(firstLine == true){
                // GET /test/TestServlet HTTP/1.1
                // Host: 127.0.0.1:8082
                String[] firstArrs = line.split(" ");
                httpServletRequest8.setProtocol("http");
                //httpServletRequest8.setRemoteAddr();

                firstLine = false;
            }else if(line.startsWith("Host:")){
                //String[] arrs =
            }
        }
    }

    public void parseResponse8()throws Exception{


    }


}
