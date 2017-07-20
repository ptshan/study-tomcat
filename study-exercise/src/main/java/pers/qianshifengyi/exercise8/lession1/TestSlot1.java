package pers.qianshifengyi.exercise8.lession1;

/**
 * Created by zhangshan on 17/7/7.
 */
public class TestSlot1 {

    /**
     * 虚拟机参数: -verbose:gc -XX:+UseSerialGC
     * @param args
     */
    public static void main(String[] args) {
        // [Full GC (System.gc())  69030K->66104K(126720K), 0.0037745 secs]
        byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();
    }


}
