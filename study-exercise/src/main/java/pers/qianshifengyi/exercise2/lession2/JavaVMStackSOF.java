package pers.qianshifengyi.exercise2.lession2;

/**
 * VM Args: -Xss128k 栈容量128k
 * 实际使用的时  -Xss128k
 * ,应该时JDK1.8的原因,128k时报错如下:
 * Error: Could not create the Java Virtual Machine.
 * The stack size specified is too small, Specify at least 160k
 * Error: A fatal exception has occurred. Program will exit.
 * Created by zhangshan on 17/6/29.
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    /**
     * 报错:Exception in thread "main" java.lang.StackOverflowError
     * 结论:在单线程下,无论栈帧太大(通过定义大量的方法帧中本地变量来实现),还是虚拟机栈容量太小(-Xss160k参数指定),
     * 当内存无法分配时,虚拟机都抛出 StackOverflowError
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args)throws Throwable {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try{
            javaVMStackSOF.stackLeak();
        }catch(Throwable e){
            // -Xss160k  时,stack length:744
            // -Xss180k  时,栈容量增加,栈深度就相应增加到了 stack length:977
            // 栈容量减少,栈的深度就相应的缩小了
            System.out.println("stack length:"+javaVMStackSOF.stackLength);
            throw e;
        }
    }


}
