package pers.qianshifengyi.tomcat.exercise6.core;

import org.apache.catalina.*;
import pers.qianshifengyi.tomcat.exercise6.core.SimpleContext6;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangshan on 17/5/23.
 */
public class SimpleContextMapper6 implements Mapper {

    private SimpleContext6 simpleContext;

    private String protocol;

    @Override
    public Container getContainer() {
        return simpleContext;
    }

    @Override
    public void setContainer(Container container) {
        simpleContext = (SimpleContext6)container;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * servletMapping(String pattern, String name)
     * findChild (container 例如wrapper  name---> wrapper )
     * @param request Request being processed
     * @param update Update the Request to reflect the mapping selection?
     * @return
     */
    @Override
    public Container map(Request request, boolean update) {
        String contextPath =
                ((HttpServletRequest) request.getRequest()).getContextPath();
        String requestURI = ((HttpRequest) request).getDecodedRequestURI();
        String relativeURI = requestURI.substring(contextPath.length());

        String name = simpleContext.findServletMapping(relativeURI);
        Wrapper wrapper = null;
        // container 实际是 wrapper
        Container container = null;
        if(name != null){
            container = simpleContext.findChild(name);
        }

        return container;
    }
}
