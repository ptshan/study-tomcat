package pers.qianshifengyi.zk;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mountain on 2017/8/10.
 */
public class ZookeeperTest {

    //String connStr = "192.168.126.130:2181,192.168.126.131:2181,192.168.126.132:2181";
    String connStr = "127.0.0.1:2181";

    @Test
    public void testConnZK()throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper(connStr, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("触发了："+watchedEvent.getType()+" 事件");
            }
        });
        System.out.println("zk:"+zooKeeper);

        String createStr = zooKeeper.create("/qjj","i am qjj,wa ha ha".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println(createStr);
        zooKeeper.close();
    }

    @Test
    public void testConnZK2()throws Exception{

        CountDownLatch countDownLatch = new CountDownLatch(1);

        ZooKeeper zk = new ZooKeeper(connStr,5000,new Watcher(){


            @Override
            public void process(WatchedEvent event) {
                if(event.getState() == Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();
                    System.out.println(" event.getState  countDown");
                }
            }
        });

        System.out.println("zk:"+zk);
        System.out.println("哎呦我去，神马情况");
        Thread.sleep(10000);
        // 等待连接完成
        countDownLatch.await();
        System.out.println("-- over");

    }

    @Test
    public void testGetNodeData()throws Exception{
        ZooKeeper zk = new ZooKeeper(connStr, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("event:"+event);
            }
        });
        List<String> children = zk.getChildren("/",false);
        children.stream().forEach(System.out::println);

        byte[] data = zk.getData("/qjj", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("event sha :"+event);
            }
        },null);

        System.out.println(new String(data));

    }

    @Test
    public void testZK3()throws Exception{
        ZooKeeper zk = new ZooKeeper(connStr, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("触发了：watcher event1:"+event);
            }
        });

        // 创建一个目录节点
        String createStr = zk.create("/nanxin","i am nanxin".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println("createStr:"+createStr);

        // 创建一个子目录节点
        String createStr2 = zk.create("/nanxin/xinxigongcheng","i am xinxigongcheng".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println("createStr2:"+createStr2);

        String createStr3 = zk.create("/nanxin/dianzixinxi","i am dianzixinxi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        System.out.println("createStr3:"+createStr3);

        // 取出各节点数据
        System.out.println("nanxinData:"+new String(zk.getData("/nanxin",false,null)));
        System.out.println("xinxigongchengData:"+new String(zk.getData("/nanxin/xinxigongcheng",false,null)));
        System.out.println("dianzixinxiData:"+new String(zk.getData("/nanxin/dianzixinxi",false,null)));
        System.out.println("------------------------------------------");

        zk.getChildren("/",false).forEach(str->{System.out.println("跟路径/下的路径："+str);});
        zk.getChildren("/nanxin",false).forEach(str->{System.out.println("/nanxin下的路径："+str);});

        //修改子节点数据
        zk.setData("/nanxin/dianzixinxi","i am dianzixinxi666".getBytes(),-1);
        System.out.println("dianzixinxiData:"+new String(zk.getData("/nanxin/dianzixinxi",false,null)));



        //删除子目录
        zk.delete("/nanxin/dianzixinxi",-1);

        //关闭zk
        zk.close();

    }

    @Test
    public void testExists()throws Exception{

        ZooKeeper zk = new ZooKeeper(connStr, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("event:"+event);
            }
        });

        System.out.println("    检查节点是否存在 zk.exists(\"/nanxin/xinxigongcheng\",false)"+zk.exists("/nanxin/xinxigongcheng",false));
        System.out.println("    检查节点是否存在 zk.exists(\"/nanxin/dianzixinxi\",false)"+zk.exists("/nanxin/dianzixinxi",false));


        zk.close();

    }




}
