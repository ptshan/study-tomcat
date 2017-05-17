package pers.qianshifengyi.tomcat.exercise5.valves;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by zhangshan on 17/5/16.
 */
public class HeaderLoggerValve  implements Valve,Contained {

    private Container container;

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public String getInfo() {
        return "this is HeaderLoggerValve";
    }

    @Override
    public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {
        valveContext.invokeNext(request,response);
        HttpServletRequest hsreq = null ;
        if(request instanceof HttpServletRequest){
            hsreq = (HttpServletRequest)request;
        }else{
            System.out.println("HeaderLoggerValve 不是合法 http");
        }

        if(hsreq != null) {
            Enumeration<String> headers = hsreq.getHeaderNames();
            while(headers.hasMoreElements()){
                String headerName = headers.nextElement();
                String headerValue = hsreq.getHeader(headerName);
                System.out.println("headerName:"+headerName+"  , headerValue:"+headerValue);
            }
        }
        System.out.println("--------------- HeaderLoggerValve end -----------------");
    }

}
