package pers.qianshifengyi.tomcat.exercise6;

import org.apache.catalina.*;
import org.apache.catalina.net.ServerSocketFactory;
import pers.qianshifengyi.tomcat.exercise5.core.SimpleWrapper;
import pers.qianshifengyi.tomcat.exercise5.http.ServerSocketFactoryImpl;
import pers.qianshifengyi.tomcat.exercise6.core.*;
import pers.qianshifengyi.tomcat.exercise6.http.HttpConnector6;
import pers.qianshifengyi.tomcat.exercise6.valves.ClientIPLoggerValve6;
import pers.qianshifengyi.tomcat.exercise6.valves.HeaderLoggerValve6;
import pers.qianshifengyi.tomcat.exercise6.valves.SimpleContextValve6;

/**
 * Created by zhangshan on 17/6/22.
 */
public class Bootstrap6 {

    public static void main(String[] args)throws Exception {
        HttpConnector6 httpConnector = new HttpConnector6();

        ServerSocketFactory ssf = new ServerSocketFactoryImpl();

        httpConnector.setFactory(ssf);
        SimpleContext6 simpleContext6 = new SimpleContext6();

        SimpleWrapperLifecycleListener6 simpleWrapperLifecycleListener6 = new SimpleWrapperLifecycleListener6();
        SimpleWrapper6 wrapper1 = new SimpleWrapper6();
        wrapper1.setName("TestServlet");
        wrapper1.setServletClass("pers.qianshifengyi.web.app.servlet.TestServlet");
        wrapper1.addLifecycleListener(simpleWrapperLifecycleListener6);

        SimpleWrapper6 wrapper2 = new SimpleWrapper6();
        wrapper2.setName("TestServlet2");
        wrapper2.setServletClass("pers.qianshifengyi.web.app.servlet.TestServlet2");
        wrapper2.addLifecycleListener(simpleWrapperLifecycleListener6);

        simpleContext6.addServletMapping("/TestServlet","TestServlet");
        simpleContext6.addServletMapping("/TestServlet2","TestServlet2");

        simpleContext6.addChild(wrapper1);
        simpleContext6.addChild(wrapper2);


        SimpleContextMapper6 mapper6 = new SimpleContextMapper6();

        mapper6.setProtocol("http");
        mapper6.setContainer(simpleContext6);

        simpleContext6.addMapper(mapper6);

        LifecycleListener lifecycleListener = new SimpleContextLifecycleListener6();
        simpleContext6.addLifecycleListener(lifecycleListener);

        Valve v1 = new ClientIPLoggerValve6();
        Valve v2 = new HeaderLoggerValve6();
        //Valve v3 = new BasicInvokeLoaderValve();
        Loader loader = new SimpleLoader6();

        simpleContext6.setLoader(loader);

        ((Contained)v1).setContainer(simpleContext6);
        ((Contained)v2).setContainer(simpleContext6);

        simpleContext6.setBasic(new SimpleContextValve6());

        simpleContext6.addValve(v1);
        simpleContext6.addValve(v2);

        httpConnector.setContainer(simpleContext6);
        httpConnector.initialize();
        httpConnector.start();
        simpleContext6.start();
        System.in.read();
    }

}
