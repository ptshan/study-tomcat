package pers.qianshifengyi.tomcat.exercise5;

import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.net.ServerSocketFactory;
import pers.qianshifengyi.tomcat.exercise5.core.SimpleLoader;
import pers.qianshifengyi.tomcat.exercise5.core.SimpleWrapper;
import pers.qianshifengyi.tomcat.exercise5.http.HttpConnector5;
import pers.qianshifengyi.tomcat.exercise5.http.ServerSocketFactoryImpl;
import pers.qianshifengyi.tomcat.exercise5.valves.BasicInvokeLoaderValve;
import pers.qianshifengyi.tomcat.exercise5.valves.ClientIPLoggerValve;
import pers.qianshifengyi.tomcat.exercise5.valves.HeaderLoggerValve;
import org.apache.catalina.*;
import pers.qianshifengyi.tomcat.util.Constants;

import java.io.IOException;

/**
 * Created by zhangshan on 17/5/16.
 */
public class Bootstrap5 {

    public static void main(String[] args) throws LifecycleException, Exception {

        HttpConnector5 httpConnector = new HttpConnector5();

        ServerSocketFactory ssf = new ServerSocketFactoryImpl();

        httpConnector.setFactory(ssf);

        Wrapper wrapper = new SimpleWrapper();
        Loader loader = new SimpleLoader();
        wrapper.setLoader(loader);
        wrapper.setServletClass("pers.qianshifengyi.web.app.servlet.TestServlet");

        Valve v1 = new ClientIPLoggerValve();
        Valve v2 = new HeaderLoggerValve();
        Valve v3 = new BasicInvokeLoaderValve();

        ((Contained)v1).setContainer(wrapper);
        ((Contained)v2).setContainer(wrapper);
        ((Contained)v3).setContainer(wrapper);

        ((Pipeline)wrapper).addValve(v1);
        ((Pipeline)wrapper).addValve(v2);
        ((Pipeline)wrapper).setBasic(v3);

        httpConnector.setContainer(wrapper);
        httpConnector.initialize();
        httpConnector.start();
        System.in.read();

    }

}
