package pers.qianshifengyi.exercise7.lession5;

/**
 * Created by mountain on 2017/6/25.
 */
public class DeadLoopClassTest {

    static class DeadLoopClass{
        static{
            if(true){
                System.out.println(Thread.currentThread()+"  init DeadLoopClass");
                while(true){

                }
            }
        }
    }

    /**
     * 运行结果：
     * Thread[thread1,5,main] start
     * Thread[thread2,5,main] start
     * Thread[thread2,5,main]  init DeadLoopClass
     * 结论说明：
     * 如果有多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的clinit方法，其他线程都需要阻塞等待，直到
     * 活动线程执行完clinit方法完毕，如果在一个类的clinit方法中有耗时很长的操作，就可能造成多个线程阻塞。
     *
     * 注意：其它线程虽然会被阻塞，但如果执行clinit方法的那条线程退出clinit方法后，其它线程被唤醒后不会再次进入clinit方法，
     * 同一个类加载器下，一个类型只会被初始化一次
     *
     * @param args
     */
    public static void main(String[] args) {
        Runnable script = new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread()+" start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread()+" run over");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.setName("thread1");
        thread2.setName("thread2");

        thread1.start();
        thread2.start();
    }


}
