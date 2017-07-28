package pers.qianshifengyi.exercise4.lession3;

/**
 * Created by zhangshan on 17/7/4.
 */
public class TestDeadLock {

    static class SynAddRunnable implements Runnable{
        int a, b;
        public SynAddRunnable(int a,int b){
            this.a = a;
            this.b = b;
        }


        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized ((Integer.valueOf(b))){
                    System.out.println("a+b="+(a+b));
                }
            }
        }
    }

    public static void main(String[] args) {

        for(int i=0;i<1000;i++) {
            new Thread(new SynAddRunnable(1,2)).start();
            new Thread(new SynAddRunnable(2,1)).start();
        }
    }



}
