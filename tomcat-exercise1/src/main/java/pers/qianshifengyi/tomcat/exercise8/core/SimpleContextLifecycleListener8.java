package pers.qianshifengyi.tomcat.exercise8.core;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * Created by zhangshan on 17/5/26.
 */
public class SimpleContextLifecycleListener8 implements LifecycleListener{

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        switch (event.getType()){
            case Lifecycle.BEFORE_START_EVENT:
                System.out.println(event.getLifecycle()+"    BEFORE_START_EVENT");
                break;
            case Lifecycle.START_EVENT:
                System.out.println(event.getLifecycle()+"    START_EVENT");
                break;
            case Lifecycle.AFTER_START_EVENT:
                System.out.println(event.getLifecycle()+"    AFTER_START_EVENT");
                break;
            default:
                System.out.println(event.getLifecycle()+"    不是开始event,event.getType():"+event.getType());
        }
    }

}
