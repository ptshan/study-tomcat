package pers.qianshifengyi.tomcat.exercise6.valves;

import org.apache.catalina.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by zhangshan on 17/5/25.
 */
public class SimpleWrapperValve implements Valve,Contained {

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
        return null;
    }

    @Override
    public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {
        Wrapper wrapper = (Wrapper)getContainer();
        Servlet servlet = wrapper.allocate();
        servlet.service(request.getRequest(),response.getResponse());
    }
}
