package pers.qianshifengyi.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mountain on 2017/8/19.
 */
public class WatcherRecoverTest {

    String connStr = "127.0.0.1:2181";

    private ZooKeeper zooKeeper;

    CountDownLatch latch = new CountDownLatch(1);

    @Before
    public void testConnZK()throws Exception{
        long sessionId = 0;
        byte[] sessionPwd = "".getBytes();

    }


}
