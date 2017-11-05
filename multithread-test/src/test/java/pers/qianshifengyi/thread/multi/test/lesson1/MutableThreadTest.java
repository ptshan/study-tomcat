package pers.qianshifengyi.thread.multi.test.lesson1;

import org.junit.Test;

/**
 * Created by mountain on 2017/9/23.
 */
public class MutableThreadTest {

    @Test
    public void testMutableThread1() throws Exception{

        MutableInteger1 mutableInteger1 = new MutableInteger1();
        MutableThreadA mutableThreadA = new MutableThreadA(mutableInteger1);
        MutableThreadB mutableThreadB = new MutableThreadB(mutableInteger1);
        mutableThreadA.start();
        mutableThreadB.start();


        Thread.sleep(2000);
    }

}
