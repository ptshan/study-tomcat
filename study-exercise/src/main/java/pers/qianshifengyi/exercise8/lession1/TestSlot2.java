package pers.qianshifengyi.exercise8.lession1;

/**
 * Created by zhangshan on 17/7/7.
 */
public class TestSlot2 {

    /**
     * 虚拟机参数: -verbose:gc  -XX:+UseSerialGC
     * @param args
     */
    public static void main(String[] args) {
        // 让placeholder超出作用域,目的是让垃圾回收进行收集,实际是仍然没有回收
        // [Full GC (System.gc())  69030K->66104K(126720K), 0.0037745 secs]
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }

        System.gc();
    }


}
