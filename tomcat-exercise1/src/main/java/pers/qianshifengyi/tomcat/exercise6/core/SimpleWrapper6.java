package pers.qianshifengyi.tomcat.exercise6.core;

import org.apache.catalina.*;
import org.apache.catalina.util.LifecycleSupport;
import pers.qianshifengyi.tomcat.exercise5.core.SimplePipeline;
import pers.qianshifengyi.tomcat.exercise6.valves.SimpleWrapperValve;

import javax.naming.directory.DirContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * 其pipeline应该没用,(用了),用于在simpleWrapper6中调用构造方法,在构造方法中,调用this.setBasic(new SimpleWrapperValve());
 * 其中,this.setBasic方法会将SimpleWrapperValve中的container设为当前的SimpleWrapper6,
 * SimpleWrapperValve类作用是,在invoke方法中,通过getContainer获取wrapper,调用servlet.service方法
 * ClientIPLoggerValve6 和 HeaderLoggerValve6 放在SimpleContext6中
 *
 *
 *
 * Created by zhangshan on 17/5/22.
 */
public class SimpleWrapper6 implements Wrapper,Lifecycle,Pipeline{

    private LifecycleSupport lifecycleSupport = new LifecycleSupport(this);

    private String servletClassName;

    private Pipeline pipeline = new SimplePipeline6();

    private ClassLoader parentClassLoader;

    private Container container;

    private Loader loader;

    private String name;

    public SimpleWrapper6(){
        this.setBasic(new SimpleWrapperValve());
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public Valve getBasic() {
        return pipeline.getBasic();
    }

    @Override
    public void setBasic(Valve valve) {
        if(valve instanceof Contained){
            ((Contained) valve).setContainer(this);
        }
        pipeline.setBasic(valve);
    }

    @Override
    public void addValve(Valve valve) {
        if(valve instanceof Contained){
            ((Contained) valve).setContainer(this);
        }
        pipeline.addValve(valve);
    }

    @Override
    public Valve[] getValves() {
        return pipeline.getValves();
    }

    @Override
    public void removeValve(Valve valve) {
        pipeline.removeValve(valve);
    }

    @Override
    public long getAvailable() {
        return 0;
    }

    @Override
    public void setAvailable(long available) {

    }

    @Override
    public String getJspFile() {
        return null;
    }

    @Override
    public void setJspFile(String jspFile) {

    }

    @Override
    public int getLoadOnStartup() {
        return 0;
    }

    @Override
    public void setLoadOnStartup(int value) {

    }

    @Override
    public String getRunAs() {
        return null;
    }

    @Override
    public void setRunAs(String runAs) {

    }

    @Override
    public String getServletClass() {
        return this.servletClassName;
    }

    @Override
    public void setServletClass(String servletClass) {
        this.servletClassName = servletClass;
    }

    @Override
    public boolean isUnavailable() {
        return false;
    }

    @Override
    public void addInitParameter(String name, String value) {

    }

    @Override
    public void addInstanceListener(InstanceListener listener) {

    }

    @Override
    public void addSecurityReference(String name, String link) {

    }

    @Override
    public Servlet allocate() throws ServletException {
        Servlet currServlet = null;
        Loader loaderTemp = getLoader();
        if(loaderTemp == null){
            throw new ServletException("SimpleWrapper6.getLoader.occur_null_exception");
        }
        ClassLoader classLoader = loaderTemp.getClassLoader();

        try {
            Class clazz = classLoader.loadClass(servletClassName);
            Object servletObj = clazz.newInstance();
            if(servletObj instanceof Servlet){
                currServlet = (Servlet)servletObj;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("SimpleWrapper6.loader.classLoader.occur_file_not_found_exception");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return currServlet;
    }

    @Override
    public void deallocate(Servlet servlet) throws ServletException {

    }

    @Override
    public String findInitParameter(String name) {
        return null;
    }

    @Override
    public String[] findInitParameters() {
        return new String[0];
    }

    @Override
    public String findSecurityReference(String name) {
        return null;
    }

    @Override
    public String[] findSecurityReferences() {
        return new String[0];
    }

    @Override
    public void load() throws ServletException {

        Servlet servlet = allocate();

        servlet.init(null);

    }

    @Override
    public void removeInitParameter(String name) {

    }

    @Override
    public void removeInstanceListener(InstanceListener listener) {

    }

    @Override
    public void removeSecurityReference(String name) {

    }

    @Override
    public void unavailable(UnavailableException unavailable) {

    }

    @Override
    public void unload() throws ServletException {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public Loader getLoader() {
        if(loader != null){
            return loader;
        }else if(container != null) {
            return container.getLoader();
        }
        return null;
    }

    @Override
    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public void setLogger(Logger logger) {

    }

    @Override
    public Manager getManager() {
        return null;
    }

    @Override
    public void setManager(Manager manager) {

    }

    @Override
    public Cluster getCluster() {
        return null;
    }

    @Override
    public void setCluster(Cluster cluster) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Container getParent() {
        return this.container;
    }

    @Override
    public void setParent(Container container) {
        this.container = container;
    }

    @Override
    public ClassLoader getParentClassLoader() {
        return this.parentClassLoader;
    }

    @Override
    public void setParentClassLoader(ClassLoader parent) {
        this.parentClassLoader = parent;
    }

    @Override
    public Realm getRealm() {
        return null;
    }

    @Override
    public void setRealm(Realm realm) {

    }

    @Override
    public DirContext getResources() {
        return null;
    }

    @Override
    public void setResources(DirContext resources) {

    }

    @Override
    public void addChild(Container child) {

    }

    @Override
    public void addContainerListener(ContainerListener listener) {

    }

    @Override
    public void addMapper(Mapper mapper) {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public Container findChild(String name) {
        return null;
    }

    @Override
    public Container[] findChildren() {
        return new Container[0];
    }

    @Override
    public ContainerListener[] findContainerListeners() {
        return new ContainerListener[0];
    }

    @Override
    public Mapper findMapper(String protocol) {
        return null;
    }

    @Override
    public Mapper[] findMappers() {
        return new Mapper[0];
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        pipeline.invoke(request,response);

    }

    @Override
    public Container map(Request request, boolean update) {
        return null;
    }

    @Override
    public void removeChild(Container child) {

    }

    @Override
    public void removeContainerListener(ContainerListener listener) {

    }

    @Override
    public void removeMapper(Mapper mapper) {

    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycleSupport.addLifecycleListener(listener);
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
        lifecycleSupport.fireLifecycleEvent(Lifecycle.BEFORE_START_EVENT,null);
        System.out.println("------------------------------------------------wrapper before start:"+name);
        if(loader instanceof Lifecycle){
            ((Lifecycle) loader).start();
        }

        if(pipeline instanceof Lifecycle){
            ((Lifecycle) pipeline).start();
        }

        lifecycleSupport.fireLifecycleEvent(Lifecycle.START_EVENT,null);
        System.out.println("------------------------------------------------wrapper  start:"+name);

        lifecycleSupport.fireLifecycleEvent(Lifecycle.AFTER_START_EVENT,null);
        System.out.println("------------------------------------------------wrapper after start:"+name);
    }

    @Override
    public void stop() throws LifecycleException {
        lifecycleSupport.fireLifecycleEvent(Lifecycle.BEFORE_STOP_EVENT,null);
        System.out.println("------------------------------------------------");
        if(pipeline instanceof Lifecycle){
            ((Lifecycle) pipeline).stop();
        }

        if(loader instanceof Lifecycle){
            ((Lifecycle) loader).stop();
        }
        lifecycleSupport.fireLifecycleEvent(Lifecycle.STOP_EVENT,null);
        System.out.println("------------------------------------------------");
        lifecycleSupport.fireLifecycleEvent(Lifecycle.AFTER_STOP_EVENT,null);

    }
}
