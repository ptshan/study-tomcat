package pers.qianshifengyi.tomcat.exercise18;

import org.apache.catalina.Lifecycle;

/**
 * Created by zhangshan on 17/6/14.
 */
public class LifecycleTest {

    public static void main(String[] args)throws Exception {
        Lifecycle lifecycle = new ZSLifecycle();
        lifecycle.addLifecycleListener(new ZSLifecycleListener());

        lifecycle.start();
    }


}
