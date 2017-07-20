package pers.qianshifengyi.exercise2.lession3;

/**
 * VM Args: -Xss20M
 * Created by zhangshan on 17/6/29.
 */
public class JavaVMStackOOM {

    private void dontStop(){
        while(true){}
    }

    public void stackLeakByThread(){
        while(true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM javaVMStackOOM = new JavaVMStackOOM();
        javaVMStackOOM.stackLeakByThread();
    }


}
