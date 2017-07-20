package pers.qianshifengyi.exercise3.lession2;

/**
 * Created by zhangshan on 17/7/3.
 */
public class TestEdenSurvivor {

    private static final int _1MB = 1024 * 1024;


    /**
     * VM 参数:-verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     */
    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];

    }

    /**
     * 默认使用的是 Parallel Scavenge
     * allocation4 4MB 结果
     * Heap
     * PSYoungGen      total 9216K, used 7987K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
     * eden space 8192K, 97% used [0x00000007bf600000,0x00000007bfdccf30,0x00000007bfe00000)
     * from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
     * to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
     * ParOldGen       total 10240K, used 4096K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     * object space 10240K, 40% used [0x00000007bec00000,0x00000007bf000010,0x00000007bf600000)
     * Metaspace       used 3088K, capacity 4494K, committed 4864K, reserved 1056768K
     * class space    used 337K, capacity 386K, committed 512K, reserved 1048576K
     *
     * Process finished with exit code 0
     *
     * allocation4 5MB 结果
     * Heap
     * PSYoungGen      total 9216K, used 7987K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
     * eden space 8192K, 97% used [0x00000007bf600000,0x00000007bfdccf30,0x00000007bfe00000)
     * from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
     * to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
     * ParOldGen       total 10240K, used 5120K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     * object space 10240K, 50% used [0x00000007bec00000,0x00000007bf100010,0x00000007bf600000)
     * Metaspace       used 3144K, capacity 4494K, committed 4864K, reserved 1056768K
     * class space    used 341K, capacity 386K, committed 512K, reserved 1048576K
     *
     * Process finished with exit code 0
     *
     * 书上例子是使用SerialGC,下面使用参数-XX:+UseSerialGC 来设置
     *
     *  下面是 allocation4 4MB 结果
     * [GC (Allocation Failure) [DefNew: 7823K->566K(9216K), 0.0055653 secs] 7823K->6710K(19456K), 0.0055922 secs] [Times: user=0.00 sys=0.01, real=0.01 secs]
     * Heap
     * def new generation   total 9216K, used 4799K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     * eden space 8192K,  51% used [0x00000007bec00000, 0x00000007bf022408, 0x00000007bf400000)
     * from space 1024K,  55% used [0x00000007bf500000, 0x00000007bf58d830, 0x00000007bf600000)
     * to   space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
     * tenured generation   total 10240K, used 6144K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
     * the space 10240K,  60% used [0x00000007bf600000, 0x00000007bfc00030, 0x00000007bfc00200, 0x00000007c0000000)
     * Metaspace       used 3055K, capacity 4494K, committed 4864K, reserved 1056768K
     * class space    used 333K, capacity 386K, committed 512K, reserved 1048576K
     *
     *
     *
     * 下面是 allocation4 5MB 结果
     * [GC (Allocation Failure) [DefNew: 7814K->569K(9216K), 0.0064031 secs] 7814K->6713K(19456K), 0.0064354 secs] [Times: user=0.01 sys=0.01, real=0.00 secs]
     * Heap
     * def new generation   total 9216K, used 5744K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     * eden space 8192K,  63% used [0x00000007bec00000, 0x00000007bf10dbf8, 0x00000007bf400000)
     * from space 1024K,  55% used [0x00000007bf500000, 0x00000007bf58e7a8, 0x00000007bf600000)
     * to   space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
     * tenured generation   total 10240K, used 6144K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
     * the space 10240K,  60% used [0x00000007bf600000, 0x00000007bfc00030, 0x00000007bfc00200, 0x00000007c0000000)
     * Metaspace       used 3111K, capacity 4494K, committed 4864K, reserved 1056768K
     * class space    used 338K, capacity 386K, committed 512K, reserved 1048576K
     *
     * Process finished with exit code 0
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        testAllocation();
    }


}
