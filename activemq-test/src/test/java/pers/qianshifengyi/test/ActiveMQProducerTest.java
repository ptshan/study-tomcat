package pers.qianshifengyi.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageConsumer;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.ActiveMQSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jms.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * Created by mountain on 2017/8/20.
 */
public class ActiveMQProducerTest {

    private ActiveMQConnectionFactory activeMQConnectionFactory;

    private Connection connection;

    private Session session;

    private MessageProducer messageProducer;

    private ActiveMQMessageConsumer activeMQMessageConsumer;

    private String connStr = "tcp://127.0.0.1:61616";

    @Before
    public void init()throws Exception{
        activeMQConnectionFactory = new ActiveMQConnectionFactory(connStr);
        connection = activeMQConnectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

    }

    @Test
    public void testPublishSingle() throws Exception{

        final CountDownLatch latch = new CountDownLatch(100);
        ExecutorService fixedExecutor = Executors.newFixedThreadPool(10);

        for(int i=1;i<=100;i++) {
            fixedExecutor.execute(()->{
                try {
                    Destination destination = session.createTopic("ZS.SingleTopic");
                    MapMessage mapMessage = session.createMapMessage();
                    int j = ThreadLocalRandom.current().nextInt(10000);
                    mapMessage.setString("name", "miao_" + j);
                    mapMessage.setInt("age", j);

                    session.createProducer(null).send(destination, mapMessage);
                    System.out.println("生产者线程--- ThreadName:"+Thread.currentThread().getName()+"  name:"+mapMessage.getString("name")
                            + "  age:"+mapMessage.getInt("age"));
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
                    latch.countDown();
                }catch(Exception e){
                    e.printStackTrace();
                }

            });

        }
        latch.await();
    }

    @Test
    public void testPublish() throws Exception{

        Destination[] destinations = TopicGenerator.getDestinations(session);
        for(int i=0;i< destinations.length;i++){
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("name","xiaoqq_"+i);
            mapMessage.setInt("age",i);

            messageProducer.send(destinations[i],mapMessage);
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
        }
    }



    @After
    public void destory()throws Exception{
        if(messageProducer != null) {
            messageProducer.close();
        }
        if(session != null) {
            session.close();
        }
        if(connection != null) {
            connection.close();
        }
    }


}
