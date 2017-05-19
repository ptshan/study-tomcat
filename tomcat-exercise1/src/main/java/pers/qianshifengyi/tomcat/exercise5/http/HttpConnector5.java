package pers.qianshifengyi.tomcat.exercise5.http;

import org.apache.catalina.*;
import org.apache.catalina.net.ServerSocketFactory;
import pers.qianshifengyi.tomcat.exercise1.Request1;
import pers.qianshifengyi.tomcat.exercise1.Response1;
import pers.qianshifengyi.tomcat.util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Created by zhangshan on 17/5/17.
 */
public class HttpConnector5 implements Runnable {

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
            while(true){
                Socket socket = serverSocket.accept();

                HttpProcessor5 httpProcessor5 = new HttpProcessor5();
                httpProcessor5.setSocket(socket);
                httpProcessor5.parseRequest();
                httpProcessor5.parseResponse();
                System.out.println("container:"+container);
                container.invoke(httpProcessor5.getRequest5(),httpProcessor5.getHttpResponse5());
                System.out.println("HttpConnector5 requestUri:"+httpProcessor5.getRequest5().getRequestURI());
                System.out.println("HttpConnector5 httpProcessor5.getRequest5().getRequest():"+httpProcessor5.getRequest5().getRequest());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
