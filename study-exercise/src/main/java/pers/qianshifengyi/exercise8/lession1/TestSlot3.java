package pers.qianshifengyi.exercise8.lession1;

/**
 * Created by zhangshan on 17/7/7.
 */
public class TestSlot3 {

    /**
     * 虚拟机参数: -verbose:gc  -XX:+UseSerialGC
     * 垃圾收集器收集了
     * [Full GC (System.gc())  69030K->568K(126720K), 0.0031829 secs]
     * @param args
     */
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }

        int a = 0;
        System.gc();
    }

}
