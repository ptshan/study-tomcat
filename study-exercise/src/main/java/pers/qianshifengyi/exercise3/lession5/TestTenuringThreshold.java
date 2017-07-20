package pers.qianshifengyi.exercise3.lession5;

/**
 * Created by zhangshan on 17/7/3.
 */
public class TestTenuringThreshold {

    private static final int _1MB = 1024 * 1024;

    /**
     * 参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintTenuringDistribution -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     *
     *
     */
    public static void testTenuringThreshold(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1MB/4];
        allocation2 = new byte[_1MB/4];

        allocation3 = new byte[_1MB * 4];
        allocation4 = new byte[_1MB * 4];

        allocation4 = null;
        allocation4 = new byte[_1MB * 4];

    }

    public static void main(String[] args) {
        testTenuringThreshold();
    }


}
