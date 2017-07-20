package pers.qianshifengyi.exercise4.lession2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by zhangshan on 17/7/4.
 */
public class ThreadMonitor {

    /**
     * 线程死循环演示
     */
    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                    ;
            }
        },"testBusyThread1");
        thread.start();
    }

    /**
     * 线程锁等待演示
     */
    public static void createLockThread(final Object lock){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try{
                        lock.wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        },"testLockThread1");
        thread.start();
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }



}
