package pers.qianshifengyi.tomcat.exercise15;

import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.ContextConfig;

/**
 * Created by zhangshan on 17/6/13.
 */
public class Bootstrap15 {

    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("user.dir"));
        System.setProperty("catalina.base",System.getProperty("user.dir"));

        Connector connector =  new HttpConnector();
        Context context = new StandardContext();
        context.setPath("/webapp-test");
        context.setDocBase("webapp-test");

        LifecycleListener contextConfigListener = new ContextConfig();
        if(context instanceof Lifecycle){
            ((Lifecycle) context).addLifecycleListener(contextConfigListener);
        }

        Host host = new StandardHost();
        host.addChild(context);
        host.setName("localhost");
        //

        /**
         *
         * System.setProperty("catalina.base",System.getProperty("user.dir"));
         * context.setPath("/webapp-test");
         * context.setDocBase("webapp-test");
         * Document base /Users/zhangshan193/Documents/projects/git_project/study-tomcat/tomcat-exercise1/webapps/webapp-test
         * host.setAppBase("webapps");
         */
        host.setAppBase("");

        Loader loader = new WebappLoader();
        context.setLoader(loader);

        connector.setContainer(host);

        connector.initialize();
        ((Lifecycle)connector).start();
        ((Lifecycle)host).start();

        Container[] containers = context.findChildren();
        for(Container c:containers){
            System.out.println(c.getName());
        }
        System.in.read();

    }

}
