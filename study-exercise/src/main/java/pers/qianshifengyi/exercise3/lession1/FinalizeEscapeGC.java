package pers.qianshifengyi.exercise3.lession1;

/**
 * Created by zhangshan on 17/6/30.
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes, i am alive");
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable{
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对象第一次成功自救
        SAVE_HOOK = null;
        System.gc();

        //finalize队列由低优先级的Finalizer线程去执行,故需要等待一下
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, i am dead");
        }


        // 对象第二次自救,失败,因为finalize方法自救只能进行一次
        SAVE_HOOK = null;
        System.gc();

        //finalize队列由低优先级的Finalizer线程去执行,故需要等待一下
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, i am dead");
        }


    }
}
