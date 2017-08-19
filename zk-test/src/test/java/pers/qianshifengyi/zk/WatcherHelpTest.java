package pers.qianshifengyi.zk;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * WatcherTest的辅助类，用于帮助创建节点，便于watcher进行监控等操作
 * Created by mountain on 2017/8/19.
 */
public class WatcherHelpTest {

    String connStr = "127.0.0.1:2181";

    private ZooKeeper zooKeeper;

    CountDownLatch latch = new CountDownLatch(1);

    /**
     * <p>连接Zookeeper</p>
     * <pre>
     *     [关于connectString服务器地址配置]
     *     格式: 192.168.1.1:2181,192.168.1.2:2181,192.168.1.3:2181
     *     这个地址配置有多个ip:port之间逗号分隔,底层操作
     *     ConnectStringParser connectStringParser =  new ConnectStringParser(“192.168.1.1:2181,192.168.1.2:2181,192.168.1.3:2181”);
     *     这个类主要就是解析传入地址列表字符串，将其它保存在一个ArrayList中
     *     ArrayList<InetSocketAddress> serverAddresses = new ArrayList<InetSocketAddress>();
     *     接下去，这个地址列表会被进一步封装成StaticHostProvider对象，并且在运行过程中，一直是这个对象来维护整个地址列表。
     *     ZK客户端将所有Server保存在一个List中，然后随机打乱(这个随机过程是一次性的)，并且形成一个环，具体使用的时候，从0号位开始一个一个使用。
     *     因此，Server地址能够重复配置，这样能够弥补客户端无法设置Server权重的缺陷，但是也会加大风险。
     *
     *     [客户端和服务端会话说明]
     *     ZooKeeper中，客户端和服务端建立连接后，会话随之建立，生成一个全局唯一的会话ID(Session ID)。
     *     服务器和客户端之间维持的是一个长连接，在SESSION_TIMEOUT时间内，服务器会确定客户端是否正常连接(客户端会定时向服务器发送heart_beat，服务器重置下次SESSION_TIMEOUT时间)。
     *     因此，在正常情况下，Session一直有效，并且ZK集群所有机器上都保存这个Session信息。
     *     在出现网络或其它问题情况下（例如客户端所连接的那台ZK机器挂了，或是其它原因的网络闪断）,客户端与当前连接的那台服务器之间连接断了,
     *     这个时候客户端会主动在地址列表（实例化ZK对象的时候传入构造方法的那个参数connectString）中选择新的地址进行连接。
     *
     *     [会话时间]
     *     客户端并不是可以随意设置这个会话超时时间，在ZK服务器端对会话超时时间是有限制的，主要是minSessionTimeout和maxSessionTimeout这两个参数设置的。
     *     如果客户端设置的超时时间不在这个范围，那么会被强制设置为最大或最小时间。 默认的Session超时时间是在2 * tickTime ~ 20 * tickTime
     * </pre>
     **/
    @Before
    public void testConnZK()throws Exception{
        zooKeeper = new ZooKeeper(connStr, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("WatcherHelpTest zk 初始化WatchedEvent："+event);

                if(event.getState().equals(Event.KeeperState.SyncConnected)){
                    System.out.println("---- zk 连接已经建立完成  ------------");
                    latch.countDown();
                }

            }
        });
    }

    /**
     * 选择的节点目录结构为：
     * /animal
     * /animal/person1
     * /animal/person2
     * /animal/person3
     * /animal/person1/1lucy
     * /animal/person1/1lily
     * /animal/person2/2tom
     * /animal/person2/2tim
     * /animal/person3/3lintao
     * /animal/person3/3hanmeimei
     * @throws Exception
     */
    @Test
    public void testCreateCurrentNode()throws Exception{

//        if(zooKeeper.getState() == ZooKeeper.States.CONNECTING){
//            System.out.println("---- 连接正在建立 -------");
//
//        }
        latch.await();

        String nodeStr = zooKeeper.create("/animal/person1","i am person1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("nodeStr:"+nodeStr);

    }

    @Test
    public void testDeleteCurrentNode()throws Exception{

        latch.await();

        zooKeeper.delete("/animal/person1",-1);
        System.out.println(" delete /animal over");

    }

    @Test
    public void testSetCurrentNodeData() throws Exception{
        latch.await();

        zooKeeper.setData("/animal/person1","i am person1 999".getBytes(),-1);
        System.out.println(" setData /animal over");
    }

    @Test
    public void testRecover()throws Exception{



    }


}
