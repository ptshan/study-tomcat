package pers.qianshifengyi.tomcat.exercise6.http;

import org.apache.catalina.Container;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.net.ServerSocketFactory;
import pers.qianshifengyi.tomcat.util.Constants;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangshan on 17/5/17.
 */
public class HttpConnector6 implements Runnable {

    private Container container;

    private ServerSocketFactory serverSocketFactory;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public ServerSocketFactory getFactory() {
        return serverSocketFactory;
    }

    public void setFactory(ServerSocketFactory factory) {
        this.serverSocketFactory = factory;
    }

    public Request createRequest() {
        return null;
    }


    public Response createResponse() {
        return null;
    }

    public void initialize() throws LifecycleException {


    }

    public void start(){

        Thread t1 = new Thread(this);
                t1.start();

    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = serverSocketFactory.createSocket(Constants.SERVER_PORT);
            //serverSocket.setSoTimeout(3000);
            while(true){
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(3000);

                HttpProcessor6 httpProcessor6 = new HttpProcessor6();
                httpProcessor6.setSocket(socket);
                httpProcessor6.parseRequest();
                httpProcessor6.parseResponse();
                System.out.println("container:"+container);
                container.invoke(httpProcessor6.getRequest6(),httpProcessor6.getHttpResponse6());
                System.out.println("HttpConnector6 requestUri:"+httpProcessor6.getRequest6().getRequestURI());
                System.out.println("HttpConnector6 httpProcessor6.getRequest6().getRequest():"+httpProcessor6.getRequest6().getRequest());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
