package pers.qianshifengyi.exercise3.lession3;

/**
 * Created by zhangshan on 17/7/3.
 */
public class TestPretenureSizeThreshold {

    private static final int _1MB = 1024 * 1024;

    private static void testPretrnureSizeThreshold(){
        byte[] allocation4 = new byte[4 * _1MB];
    }

    /**
     * VM运行参数: -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     *
     * 3MB = 3 * 1024 * 1024 = 3145728
     *
     *
     * Heap
     * def new generation   total 9216K, used 1843K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     * eden space 8192K,  22% used [0x00000007bec00000, 0x00000007bedccd20, 0x00000007bf400000)
     * from space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
     * to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
     * tenured generation   total 10240K, used 4096K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
     * the space 10240K,  40% used [0x00000007bf600000, 0x00000007bfa00010, 0x00000007bfa00200, 0x00000007c0000000)
     * Metaspace       used 3045K, capacity 4494K, committed 4864K, reserved 1056768K
     * class space    used 333K, capacity 386K, committed 512K, reserved 1048576K
     *
     * Process finished with exit code 0
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        testPretrnureSizeThreshold();
    }

}
