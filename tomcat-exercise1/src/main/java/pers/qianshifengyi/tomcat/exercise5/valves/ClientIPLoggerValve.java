package pers.qianshifengyi.tomcat.exercise5.valves;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by zhangshan on 17/5/16.
 */
public class ClientIPLoggerValve implements Valve,Contained{

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
        ServletRequest sreq = null ;
        ServletResponse sres = null ;
        if(request instanceof ServletRequest){
            sreq = (ServletRequest)request;
            sres = (ServletResponse)response;
        }else{
            System.out.println("ClientIPLoggerValve 不是合法 http");
        }

        String remoteIp = null;
        if(sreq != null && sres != null){
            remoteIp = sreq.getRemoteAddr();
        }

        System.out.println("remoteIP:"+remoteIp);
        System.out.println("--------------- ClientIPLoggerValve end -----------------");
    }

}
