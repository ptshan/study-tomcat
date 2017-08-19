package pers.qianshifengyi.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/qq910894904/article/details/41726279
 *
 * state=-112 会话超时状态
 state= -113　认证失败状态
 state=  1 连接建立中
 state= 2 (暂时不清楚如何理解这个状态,ZOO_ASSOCIATING_STATE)
 state=3 连接已建立状态
 state= 999 无连接状态


 type=1 创建节点事件
 type=2 删除节点事件
 type=3 更改节点事件
 type=4 子节点列表变化事件
 type= -1 会话session事件
 type=-2 监控被移除事件
 对父节点的变更以及孙节点的变更都不会触发watcher，而对watcher本身节点以及子节点的变更会触发watcher，具体参照下表。

 操作	方法	触发watcher	watcher state	watcher type	watcher path
 Create当前节点	getdata	×	×	×	×
 getchildren	√	3	4	√
 exists	×	×	×	×
 set当前节点	getdata	√	3	3	√
 getchildren	×	×	×	×
 exists	√	3	3	√
 delete当前节点	getdata	√	3	2	√
 getchildren	√	3	2	√
 exists	√	3	2	√
 create子节点	getdata	×	×	×	×
 getchildren	√	3	4	√
 exists	×	×	×	×
 set子节点	getdata	×	×	×	×
 getchildren	×	×	×	×
 exists	×	×	×	×
 delete子节点	getdata	×	×	×	×
 getchildren	√	3	4	√
 exists	×	×	×	×
 恢复连接	getdata	√	1	-1	×
 getchildren	√	1	-1	×
 exists	√	1	-1	×
 恢复连接session未超时	getdata	√	-112	-1	×
 getchildren	√	-112	-1	×
 exists	√	-112	-1	×
 恢复连接session超时	getdata	√	3	-1	×
 getchildren	√	3	-1	×
 exists	√	3	-1	×
 * Created by mountain on 2017/8/19.
 */
public class WatcherTest {

    private ZooKeeper zooKeeper;

    private static final String ZK_URL="127.0.0.1:2181";

    private CountDownLatch connectedLatch = new CountDownLatch(1);

    private long sessionId = 0;
    byte[] sessionPwd  = null;

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
    @Before
    public void init()throws Exception{
        zooKeeper = new ZooKeeper(ZK_URL, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("WatcherTest zk 初始化WatchedEvent："+event);
                if(event.getState().equals(Event.KeeperState.SyncConnected)){
                    connectedLatch.countDown();
                }
            }
        });

    }

    /**
     * create当前节点
     * 方法	          触发watcher	watcher state	watcher type	watcher path
     * getdata             NO             NO             NO               NO
     * getchildren         YES            3              4                YES
     * exists              NO             NO             NO               NO
     *
     */
    @Test
    public void testCreateCurrentNode()throws Exception{
        // 等待zookeeper连接完成，再执行
        connectedLatch.await();
        sessionId = zooKeeper.getSessionId();
        sessionPwd = zooKeeper.getSessionPasswd();
        System.out.println("sessionId:"+sessionId);
        System.out.println("sessionPwd:"+new String(sessionPwd));
        CountDownLatch blockingLatch = new CountDownLatch(1);

        watch();

        // 执行15秒
        Thread.sleep(15000);
        System.out.println("--- 即将断开");
        zooKeeper.close();
        // 关闭15秒后重连
        Thread.sleep(5000);
        System.out.println("--- 即将 重连");
        zooKeeper = new ZooKeeper(ZK_URL, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("WatcherHelpTest zk 初始化WatchedEvent："+event);

                if(event.getState().equals(Event.KeeperState.SyncConnected)){
                    System.out.println("---- zk 连接已经建立完成  ------------");
                    connectedLatch.countDown();
                }

            }
        },sessionId,sessionPwd);

//        watch();

        blockingLatch.await();
    }

    private void watch()throws Exception{
        new Thread(() -> {
            for(int i=1;i<10000;i++) {
                System.out.println("getData <<<<<<<<<<<<<<第 "+i+" 次监控");
                CountDownLatch tempLatch = new CountDownLatch(1);
                try {
                    byte[] data = zooKeeper.getData("/animal", new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            System.out.println("create当前节点 getData WatchedEvent：" + event);
                            tempLatch.countDown();
                        }
                    }, null);
                    System.out.println("getData 数据为:"+new String(data));
                } catch (KeeperException e) {
                    System.out.println("getData exception:"+e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        tempLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();

        new Thread(() -> {
            for(int i=1;i<10000;i++) {
                System.out.println("getChildren <<<<<<<<<<<<<<第 " + i + " 次监控");
                CountDownLatch tempLatch = new CountDownLatch(1);
                try {
                    List<String> children = zooKeeper.getChildren("/animal", new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            System.out.println("create当前节点 getChildren WatchedEvent：" + event);
                            tempLatch.countDown();
                        }
                    });

                    if(children == null ){
                        System.out.println("children is null");
                    }else if(children.size() == 0){
                        System.out.println("children size == 0");
                    }else{
                        //children.forEach(System.out::println);
                        for(String child:children){
                            System.out.println("--> child:"+child);
                        }
                    }

                } catch (KeeperException e) {
                    System.out.println("getChildren exception:"+e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        tempLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(() -> {
            for(int i=1;i<10000;i++) {
                System.out.println("exists <<<<<<<<<<<<<<第 " + i + " 次监控");
                CountDownLatch tempLatch = new CountDownLatch(1);
                try {
                    Stat stat = zooKeeper.exists("/animal", new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            System.out.println("create当前节点 exists WatchedEvent：" + event);
                            tempLatch.countDown();
                        }
                    });
                    System.out.println("exists stat:"+stat);
                } catch (KeeperException e) {
                    System.out.println("exists exception:"+e.getMessage());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        tempLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();




        new Thread(() -> {
            for(int i=1;i<10000;i++) {
                System.out.println("getData <<<<<<<<<<<<<<第 "+i+" 次监控");
                CountDownLatch tempLatch = new CountDownLatch(1);
                try {
                    byte[] data = zooKeeper.getData("/animal/person1", new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            System.out.println("create当前节点 /animal/person1  getData WatchedEvent：" + event);
                            tempLatch.countDown();
                        }
                    }, null);
                    System.out.println("/animal/person1 getData 数据为:"+new String(data));
                } catch (KeeperException e) {
                    System.out.println("/animal/person1 getData exception:"+e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        tempLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();


        new Thread(() -> {
            for(int i=1;i<10000;i++) {
                System.out.println("exists <<<<<<<<<<<<<<第 " + i + " 次监控");
                CountDownLatch tempLatch = new CountDownLatch(1);
                try {
                    Stat stat = zooKeeper.exists("/animal/person1", new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            System.out.println("/animal/person1 create当前节点 exists WatchedEvent：" + event);
                            tempLatch.countDown();
                        }
                    });
                    System.out.println("/animal/person1 exists stat:"+stat);
                } catch (KeeperException e) {
                    System.out.println("/animal/person1 exists exception:"+e.getMessage());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        tempLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * set当前节点
     * 方法	          触发watcher	watcher state	watcher type	watcher path
     * getdata             YES             3             3               YES
     * getchildren         NO              NO            NO              NO
     * exists              YES             3             3               YES
     *
     */
    @Test
    public void testSetCurrentNode() throws Exception{

    }


    /**
     * delete当前节点
     * 方法	          触发watcher	watcher state	watcher type	watcher path
     * getdata             YES            3              2                YES
     * getchildren         YES            3              2                YES
     * exists              YES            3              2                YES
     *
     */
    @Test
    public void testDeleteCurrentNode() throws Exception{

    }

    @Test
    public void testRecoverConn() throws Exception{

        //zooKeeper.register();



    }




}
