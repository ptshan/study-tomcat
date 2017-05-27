package pers.qianshifengyi.tomcat.exercise8.core;

import org.apache.catalina.Container;
import org.apache.catalina.Mapper;
import org.apache.catalina.Request;

/**
 * Created by zhangshan on 17/5/26.
 */
public class SimpleMapper8 implements Mapper{

    private Container container;

    private String protocol;

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public Container map(Request request, boolean update) {
        return null;
    }
}
