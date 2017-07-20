package pers.qianshifengyi.exercise7.lession6;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by zhangshan on 17/6/26.
 */
public class Test1 {

    public static void main(String[] args) {
//        MyThread1 myThread1 = new MyThread1();
//        myThread1.start();
//        MyThread2 myThread2 = new MyThread2();
//        myThread2.start();
//        System.out.println("myThread1.getContextClassLoader():"+myThread1.getContextClassLoader());
//        System.out.println("myThread2.getContextClassLoader():"+myThread2.getContextClassLoader());


        new MyThread3().start();

    }

}

/**
 * ContextClassLoader,如果创建线程时还未设置,它将会从父线程中继承一个
 * 所谓继承一个,实际就是Thread.currentThread(),代码如下:
 * Thread parent = currentThread();
 * if (security == null || isCCLOverridden(parent.getClass()))
 *  this.contextClassLoader = parent.getContextClassLoader();
 * else
 *  this.contextClassLoader = parent.contextClassLoader;
 *
 *
 */
class MyThread1 extends Thread{

    public MyThread1(){
        super.setName("Parent Thread111");
        setName("Thread111");
        setContextClassLoader(new URLClassLoader(new URL[0]));
    }

    @Override
    public void run() {
        System.out.println("111 ---  "+Thread.currentThread());
        System.out.println("222 ---  "+Thread.currentThread().getContextClassLoader());
    }
}

class MyThread2 extends MyThread1{

    public MyThread2(){
        setName("Thread222");
    }

}

class MyThread3 extends MyThread1{

    public MyThread3(){
        setName("Thread333");
       // setContextClassLoader(new URLClassLoader(new URL[0]));
    }

    @Override
    public void run() {
        MyThread4 myThread4 = new MyThread4();
        myThread4.start();

    }

}

class MyThread4 extends  MyThread3{

    public MyThread4(){
        setName("Thread444");
    }

    @Override
    public void run() {
        System.out.println("-----4444getContextClassLoader():"+getContextClassLoader());

    }

}


