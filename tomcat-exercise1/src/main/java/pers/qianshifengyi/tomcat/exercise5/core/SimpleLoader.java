package pers.qianshifengyi.tomcat.exercise5.core;

import org.apache.catalina.Container;
import org.apache.catalina.DefaultContext;
import org.apache.catalina.Loader;
import pers.qianshifengyi.tomcat.util.Constants;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by zhangshan on 17/5/17.
 */
public class SimpleLoader implements Loader {

    @Override
    public ClassLoader getClassLoader() {
        try {
            URL[] urls = new URL[2];
            URLStreamHandler urlStreamHandler = null;
            File classesFile = new File(Constants.WEB_CLASSES);
            File libFile = new File(Constants.WEB_LIB);
            String repository0 = (new URL("file", null, classesFile.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository0, urlStreamHandler);

            String repository1 = (new URL("file", null, libFile.getCanonicalPath() + File.separator)).toString();
            urls[1] = new URL(null, repository1, urlStreamHandler);

            URLClassLoader classLoader = new URLClassLoader(urls);

            return classLoader;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
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
        return "this is simpleLoader";
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
