package pers.qianshifengyi.tomcat.exercise5.valves;

import org.apache.catalina.*;
import pers.qianshifengyi.tomcat.exercise5.core.SimpleWrapper;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by zhangshan on 17/5/18.
 */
public class BasicInvokeLoaderValve implements Valve,Contained {

    private Container container;

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
        System.out.println("---- >>>>  ----- setContainer    BasicInvokeLoaderValve container:"+container);
    }

    @Override
    public String getInfo() {
        return "this is BasicInvokeLoaderValve";
    }

    @Override
    public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {
        SimpleWrapper simpleWrapper = null;
        System.out.println("BasicInvokeLoaderValve container:"+container);
        if(container instanceof SimpleWrapper){
            simpleWrapper = (SimpleWrapper)container;
            Servlet servlet = simpleWrapper.allocate();
            System.out.println("servlet:"+servlet);
            servlet.service(request.getRequest(),response.getResponse());
        }else{
            System.out.println("BasicInvokeLoaderValve invoke 挂了");
        }
    }
}
