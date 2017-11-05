package pers.qianshifengyi.thread.multi.test.lesson1;

/**
 * 读取ThreadA设置的value值
 * Created by mountain on 2017/9/23.
 */
public class MutableThreadA extends Thread {

    private MutableInteger1 mutableInteger1;

    public MutableThreadA(MutableInteger1 mutableInteger1){
        this.setName("MutableThreadA");
        this.mutableInteger1 = mutableInteger1;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" run ---");
        mutableInteger1.setValue(1);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" getValue():"+mutableInteger1.getValue());
    }
}
