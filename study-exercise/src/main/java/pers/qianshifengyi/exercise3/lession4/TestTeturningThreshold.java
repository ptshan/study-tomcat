package pers.qianshifengyi.exercise3.lession4;

/**
 * Created by zhangshan on 17/7/3.
 */
public class TestTeturningThreshold {

    private static final int _1MB = 1024 * 1024;

    /**
     * 系统运行参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * 系统运行参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     *
     *
     *
     */
    public static void testTeturningThreshold(){

        byte[] allocation1,allocation2,allocation3;

        allocation1 = new byte[_1MB / 4]; // 256k
        allocation2 = new byte[_1MB * 4];
        allocation3 = new byte[_1MB * 4];

        allocation3 = null;

        allocation3 = new byte[_1MB * 4];

    }

    public static void main(String[] args) {
        testTeturningThreshold();
    }


}
