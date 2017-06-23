package pers.qianshifengyi.tomcat.exercise16;

/**
 * Created by zhangshan on 17/6/12.
 */
public class ShutdownHookDemo {

    public static void main(String[] args) throws Exception{
        // 任务管理器  退出 能执行  强制退出不能执行
        // kill -3 PID 不会干掉进程,仅会打印堆栈等内存信息,
        // kill -9 PID 野蛮杀死进程,不会调用钩子回收资源
        // kill PID 杀死进程,回收钩子资源
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());

        System.in.read();
    }
}

class ShutdownHook extends Thread{
    @Override
    public void run() {
        System.out.println("shutdownhook execute !!");
    }
}
