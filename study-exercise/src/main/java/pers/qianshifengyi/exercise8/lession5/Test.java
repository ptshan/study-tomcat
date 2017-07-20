package pers.qianshifengyi.exercise8.lession5;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 目标:在Son中调用到grandFather的thinking方法
 * Created by zhangshan on 17/7/8.
 */
public class Test {

    public static void main(String[] args) {
        new Son().thinking();
    }


}

class GrandFather{
    public void thinking(){
        System.out.println(" i am grandFather ");
    }
}

class Father extends GrandFather{
    public void thinking(){
        System.out.println(" i am father ");
    }
}


class Son extends Father{

    public void thinking(){
        // 目标:在Son中调用到grandFather的thinking方法
        MethodType mt = MethodType.methodType(void.class);

        try {
           MethodHandle methodHandle = lookup().findSpecial(GrandFather.class,"thinking",mt,getClass());
            try {
                methodHandle.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

