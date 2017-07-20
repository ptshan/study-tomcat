package pers.qianshifengyi.exercise3.lession6;

/**
 * Created by zhangshan on 17/7/3.
 */
public class TestHandlePromotion {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+UseSerialGC -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
     *
     *
     */
    public static void testHandlePromotion(){
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5,allocation6,allocation7;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];

        allocation1 = null;

        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];

        allocation4 = null;
        allocation5 = null;
        allocation6 = null;

        allocation7 = new byte[2 * _1MB];
    }

    public static void main(String[] args) {

        testHandlePromotion();

    }



}
