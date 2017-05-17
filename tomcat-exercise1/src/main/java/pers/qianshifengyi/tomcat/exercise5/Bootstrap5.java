package pers.qianshifengyi.tomcat.exercise5;

import org.apache.catalina.connector.http.HttpConnector;
import pers.qianshifengyi.tomcat.exercise5.core.SimpleLoader;
import pers.qianshifengyi.tomcat.exercise5.core.SimpleWrapper;
import pers.qianshifengyi.tomcat.exercise5.valves.ClientIPLoggerValve;
import pers.qianshifengyi.tomcat.exercise5.valves.HeaderLoggerValve;
import org.apache.catalina.*;

import java.io.IOException;

/**
 * Created by zhangshan on 17/5/16.
 */
public class Bootstrap5 {

    public static void main(String[] args) throws LifecycleException, IOException {
        HttpConnector httpConnector = new HttpConnector();

        Wrapper wrapper = new SimpleWrapper();
        Loader loader = new SimpleLoader();
        wrapper.setLoader(loader);
        wrapper.setServletClass("pers.qianshifengyi.web.app.servlet.TestServlet");

        Valve v1 = new ClientIPLoggerValve();
        Valve v2 = new HeaderLoggerValve();

        ((Pipeline)wrapper).addValve(v1);
        ((Pipeline)wrapper).addValve(v2);

        httpConnector.setContainer(wrapper);
        httpConnector.initialize();
        httpConnector.start();
        System.in.read();

    }

}
