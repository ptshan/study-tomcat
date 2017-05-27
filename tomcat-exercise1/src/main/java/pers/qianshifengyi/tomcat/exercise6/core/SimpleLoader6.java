package pers.qianshifengyi.tomcat.exercise6.core;

import org.apache.catalina.*;
import pers.qianshifengyi.tomcat.util.Constants;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Loader是一个顶层接口
 * Created by zhangshan on 17/5/22.
 */
public class SimpleLoader6 implements Loader,Lifecycle {

    private ClassLoader classLoader = null;

    private Object lock = new Object();

    @Override
    public void addLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public void start() throws LifecycleException {
        System.out.println("SimpleLoader6 start");
    }

    @Override
    public void stop() throws LifecycleException {
        System.out.println("SimpleLoader6 stop");
    }

    @Override
    public ClassLoader getClassLoader() {
        synchronized (lock) {
            if (classLoader == null) {
                try {
                    initClassLoader();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return classLoader;
        }
    }

    private void initClassLoader()throws Exception{
        URLStreamHandler urlStreamHandler = null;
        URL[] urls = new URL[2];
        String resp1 = (new URL("file",null, Constants.WEB_CLASSES)).toString()+ File.separator;
        String resp2 = (new URL("file",null,Constants.WEB_LIB)).toString()+ File.separator;
        System.out.println("resp1:"+resp1);
        System.out.println("resp2:"+resp2);
        urls[0] = new URL(null,resp1,urlStreamHandler);
        urls[1] = new URL(null,resp2,urlStreamHandler);
        classLoader = new URLClassLoader(urls);
    }

    @Override
    public Container getContainer() {
        return null;
    }

    @Override
    public void setContainer(Container container) {

    }

    @Override
    public DefaultContext getDefaultContext() {
        return null;
    }

    @Override
    public void setDefaultContext(DefaultContext defaultContext) {

    }

    @Override
    public boolean getDelegate() {
        return false;
    }

    @Override
    public void setDelegate(boolean delegate) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public boolean getReloadable() {
        return false;
    }

    @Override
    public void setReloadable(boolean reloadable) {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void addRepository(String repository) {

    }

    @Override
    public String[] findRepositories() {
        return new String[0];
    }

    @Override
    public boolean modified() {
        return false;
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }
}
