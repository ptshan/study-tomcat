package pers.qianshifengyi.thread.multi.test.lesson1;

/**
 * Created by mountain on 2017/9/23.
 */
public class MutableThreadB extends Thread {

    private MutableInteger1 mutableInteger1;

    public MutableThreadB(MutableInteger1 mutableInteger1){
        this.setName("MutableThreadB");
        this.mutableInteger1 = mutableInteger1;
    }

    @Override
    public void run() {

        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" run ---");
        mutableInteger1.setValue(2);
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" getValue():"+mutableInteger1.getValue());
    }

}
