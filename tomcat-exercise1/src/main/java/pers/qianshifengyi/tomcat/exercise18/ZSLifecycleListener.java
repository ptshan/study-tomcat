package pers.qianshifengyi.tomcat.exercise18;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * Created by zhangshan on 17/6/14.
 */
public class ZSLifecycleListener implements LifecycleListener {

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        switch (event.getType()){
            case Lifecycle.BEFORE_START_EVENT:
                System.out.println("--- ZSLifecycleListener method lifecycleEvent Lifecycle.BEFORE_START_EVENT was invoked");
                break;
            case Lifecycle.START_EVENT:
                System.out.println("--- ZSLifecycleListener method lifecycleEvent Lifecycle.START_EVENT was invoked");
                break;

        }
    }

}
