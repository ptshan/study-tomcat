package pers.qianshifengyi.tomcat.exercise18;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.util.LifecycleSupport;

/**
 * Created by zhangshan on 17/6/14.
 */
public class ZSLifecycle implements Lifecycle {

    private LifecycleSupport support = new LifecycleSupport(this);

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        support.addLifecycleListener(listener);
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
        support.fireLifecycleEvent(Lifecycle.BEFORE_START_EVENT,null);
        System.out.println("===== ZSLifecycle ---------------");
        support.fireLifecycleEvent(Lifecycle.START_EVENT,null);
    }

    @Override
    public void stop() throws LifecycleException {

    }
}
