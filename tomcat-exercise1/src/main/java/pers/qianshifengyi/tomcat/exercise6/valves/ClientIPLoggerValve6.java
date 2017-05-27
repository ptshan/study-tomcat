package pers.qianshifengyi.tomcat.exercise6.valves;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zhangshan on 17/5/16.
 */
public class ClientIPLoggerValve6 implements Valve,Contained{

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
        return "this is HeaderLoggerValve6";
    }

    @Override
    public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {
        valveContext.invokeNext(request,response);
        ServletRequest sreq = request.getRequest() ;
        ServletResponse sres = response.getResponse() ;

        System.out.println("request:"+request+"  response:"+response);
        System.out.println("request.getRequest():"+request.getRequest()+"  response.getResponse():"+response.getResponse());

        String remoteIp = null;
        if(sreq != null && sres != null){
            remoteIp = sreq.getRemoteAddr();
        }
        System.out.println("sreq.getRemoteAddr():"+sreq.getRemoteAddr());
        System.out.println("((HttpServletRequest)sreq).getRemoteAddr():"+((HttpServletRequest)sreq).getRemoteAddr());
        System.out.println("remoteIP:"+remoteIp);
        System.out.println("--------------- ClientIPLoggerValve6 end -----------------");
    }

}
