package pers.qianshifengyi.tomcat.exercise6.valves;

import org.apache.catalina.*;
import pers.qianshifengyi.tomcat.exercise6.http.HttpServletRequest6;
import pers.qianshifengyi.tomcat.exercise6.http.HttpServletResponse6;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 目的是调用context的map方法取得wrapper,调用其invoke,context是通过setContainer设置进去的
 * 该valve被当做SimpleContext的basicValve来使用的
 * Created by zhangshan on 17/5/22.
 */
public class SimpleContextValve6 implements Valve,Contained {

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
    public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {

        HttpServletRequest6 hsreq6 = null;

        HttpServletResponse6 hsres6 = null;

        if(!(request.getRequest() instanceof ServletRequest) || !(response.getResponse() instanceof ServletResponse)){
            System.out.println("SimpleContextValve6 request.getRequest() 和 response.getResponse() 异常");
            return;
        }

        hsreq6 = (HttpServletRequest6)request.getRequest();
        hsres6 = (HttpServletResponse6)response.getResponse();

        String uri = ((HttpRequest)request).getDecodedRequestURI();
        String contextPath = hsreq6.getContextPath();

        String relativeUri = uri.substring(contextPath.length());

        Wrapper wrapper = null;

        Context context = (Context)getContainer();

        try {
            wrapper = (Wrapper) context.map(request, true);
        }catch(IllegalArgumentException e){
            badRequest(relativeUri,hsres6);
            return;
        }

        if(wrapper == null){
            notFound(relativeUri,hsres6);
            return;
        }

        request.setContext(context);

        wrapper.invoke(request,response);

    }

    private void notFound(String relativeUri, HttpServletResponse6 hsres6) {
        try {
            hsres6.sendError(HttpServletResponse6.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SimpleContextValve6 notFound");
        }
    }

    private void badRequest(String relativeUri, HttpServletResponse6 hsres6) {
        try {
            hsres6.sendError(HttpServletResponse6.SC_BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SimpleContextValve6 badRequest");
        }
    }


}
