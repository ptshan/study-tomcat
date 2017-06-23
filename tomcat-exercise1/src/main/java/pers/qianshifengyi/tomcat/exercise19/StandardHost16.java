package pers.qianshifengyi.tomcat.exercise19;

import org.apache.catalina.Context;
import org.apache.catalina.DefaultContext;
import org.apache.catalina.Deployer;
import org.apache.catalina.Host;
import org.apache.catalina.core.ContainerBase;

import java.io.IOException;
import java.net.URL;

/**
 * Created by zhangshan on 17/6/15.
 */
public class StandardHost16 extends ContainerBase implements Deployer,Host{



    @Override
    public String getAppBase() {
        return null;
    }

    @Override
    public void setAppBase(String appBase) {

    }

    @Override
    public boolean getAutoDeploy() {
        return false;
    }

    @Override
    public void setAutoDeploy(boolean autoDeploy) {

    }

    @Override
    public void addDefaultContext(DefaultContext defaultContext) {

    }

    @Override
    public DefaultContext getDefaultContext() {
        return null;
    }

    @Override
    public void importDefaultContext(Context context) {

    }

    @Override
    public void addAlias(String alias) {

    }

    @Override
    public String[] findAliases() {
        return new String[0];
    }

    @Override
    public Context map(String uri) {
        return null;
    }

    @Override
    public void removeAlias(String alias) {

    }

    @Override
    public void install(String contextPath, URL war) throws IOException {

    }

    @Override
    public void install(URL config, URL war) throws IOException {

    }

    @Override
    public Context findDeployedApp(String contextPath) {
        return null;
    }

    @Override
    public String[] findDeployedApps() {
        return new String[0];
    }

    @Override
    public void remove(String contextPath) throws IOException {

    }

    @Override
    public void start(String contextPath) throws IOException {

    }

    @Override
    public void stop(String contextPath) throws IOException {

    }

    @Override
    public String getInfo() {
        return null;
    }
}
