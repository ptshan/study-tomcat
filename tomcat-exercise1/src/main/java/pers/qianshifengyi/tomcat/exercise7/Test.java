package pers.qianshifengyi.tomcat.exercise7;

/**
 * Created by zhangshan on 17/5/25.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.setProperty("catalina.base",System.getProperty("user.dir"));
        System.out.println(System.getProperty("catalina.base"));

    }


}
