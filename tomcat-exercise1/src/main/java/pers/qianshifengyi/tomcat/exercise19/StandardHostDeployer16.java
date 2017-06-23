package pers.qianshifengyi.tomcat.exercise19;

import org.apache.catalina.*;

import java.io.IOException;
import java.net.URL;

/**
 * Created by zhangshan on 17/6/15.
 */
public class StandardHostDeployer16 implements Deployer {

    private StandardHost16 standardHost16;






    public StandardHostDeployer16(StandardHost16 standardHost16){
        this.standardHost16 = standardHost16;
    }

    @Override
    public String getName() {
        return standardHost16.getName();
    }

    @Override
    public void install(String contextPath, URL war) throws IOException {

    }

    @Override
    public void install(URL config, URL war) throws IOException {

    }

    @Override
    public Context findDeployedApp(String contextPath) {
        Container child = standardHost16.findChild(contextPath);
        if(child instanceof Context){
            return (Context)child;
        }
        return null;
    }

    @Override
    public String[] findDeployedApps() {
        Container[] children = standardHost16.findChildren();
        String[] deployApps = new String[children.length];
        for(int i=0;i<children.length;i++){
            deployApps[i]=children[i].getName();
        }
        return deployApps;
    }

    @Override
    public void remove(String contextPath) throws IOException {
        if(contextPath == null || (!contextPath.startsWith("/"))){
            throw new IllegalArgumentException("contextPath 非法,contextPath:"+contextPath);
        }
        Context child = findDeployedApp(contextPath);
        standardHost16.removeChild(child);
    }

    @Override
    public void start(String contextPath) throws IOException {
        if(contextPath == null || (!contextPath.startsWith("/"))){
            throw new IllegalArgumentException("contextPath 非法,contextPath:"+contextPath);
        }
        Context context = findDeployedApp(contextPath);
        if(context instanceof Lifecycle)
            try {
                ((Lifecycle)context).start();
            } catch (LifecycleException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void stop(String contextPath) throws IOException {
        if(contextPath == null || (!contextPath.startsWith("/"))){
            throw new IllegalArgumentException("contextPath 非法,contextPath:"+contextPath);
        }
        Context context = findDeployedApp(contextPath);
        if(context instanceof Lifecycle)
            try {
                ((Lifecycle)context).stop();
            } catch (LifecycleException e) {
                e.printStackTrace();
            }
    }
}
