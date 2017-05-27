package pers.qianshifengyi.tomcat.exercise6.core;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

/**
 * Created by zhangshan on 17/5/22.
 */
public class SimpleContextLifecycleListener6 implements LifecycleListener {

    @Override
    public void lifecycleEvent(LifecycleEvent event) {

        System.out.println("SimpleContextLifecycleListener6 lifecycle:"+event.getLifecycle()
                +",type:"+event.getType()
                +",data:"+event.getData());
        System.out.print("lifecycle:"+event.getLifecycle());
        switch (event.getType()){
            case Lifecycle.AFTER_START_EVENT:
                System.out.println("    AFTER_START_EVENT");
                break;
            case Lifecycle.AFTER_STOP_EVENT:
                System.out.println("    AFTER_STOP_EVENT");
                break;
            case Lifecycle.BEFORE_START_EVENT:
                System.out.println("    BEFORE_START_EVENT");
                break;
            case Lifecycle.BEFORE_STOP_EVENT:
                System.out.println("    BEFORE_STOP_EVENT");
                break;
            case Lifecycle.START_EVENT:
                System.out.println("    START_EVENT");
                break;
            case Lifecycle.STOP_EVENT:
                System.out.println("    STOP_EVENT");
                break;
            default:
                System.out.println("    event.getType() 类型挂了");
        }
    }

}
