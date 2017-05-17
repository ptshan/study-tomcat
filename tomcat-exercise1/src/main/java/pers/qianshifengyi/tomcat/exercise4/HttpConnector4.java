package pers.qianshifengyi.tomcat.exercise4;

import org.apache.catalina.*;
import org.apache.catalina.net.ServerSocketFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshan on 17/5/11.
 */
public class HttpConnector4 implements Connector {

    private List<HttpProcessor4> processor4Queue = new ArrayList<HttpProcessor4>();

    private int minProcessors = 5;

    private int maxProcessors = 20;

    private int curProcessors;

    public synchronized void initial(){
        while(curProcessors < minProcessors){
            if(curProcessors <= maxProcessors) {
                HttpProcessor4 httpProcessor4 = null;
                processor4Queue.add(httpProcessor4);
                curProcessors++;
            }else{
                break;
            }

        }

    }




    @Override
    public void initialize() throws LifecycleException {

    }

    private Container container;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public boolean getEnableLookups() {
        return false;
    }

    @Override
    public void setEnableLookups(boolean enableLookups) {

    }

    @Override
    public ServerSocketFactory getFactory() {
        return null;
    }

    @Override
    public void setFactory(ServerSocketFactory factory) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public int getRedirectPort() {
        return 0;
    }

    @Override
    public void setRedirectPort(int redirectPort) {

    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public void setScheme(String scheme) {

    }

    @Override
    public boolean getSecure() {
        return false;
    }

    @Override
    public void setSecure(boolean secure) {

    }

    @Override
    public Service getService() {
        return null;
    }

    @Override
    public void setService(Service service) {

    }

    @Override
    public Request createRequest() {
        return null;
    }

    @Override
    public Response createResponse() {
        return null;
    }

    public int getMinProcessors() {
        return minProcessors;
    }

    public void setMinProcessors(int minProcessors) {
        this.minProcessors = minProcessors;
    }

    public int getMaxProcessors() {
        return maxProcessors;
    }

    public void setMaxProcessors(int maxProcessors) {
        this.maxProcessors = maxProcessors;
    }
}
