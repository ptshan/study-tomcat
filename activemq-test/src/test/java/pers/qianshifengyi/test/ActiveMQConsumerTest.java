package pers.qianshifengyi.test;

import org.apache.activemq.ActiveMQConnectionFactory;
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
public class ActiveMQConsumerTest {

    private ActiveMQConnectionFactory factory;

    private Connection connection;

    private Session session;

    private MessageConsumer consumer;

    private String connStr = "tcp://127.0.0.1:61616";

    @Before
    public void init()throws Exception{
        factory = new ActiveMQConnectionFactory(connStr);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testSingleConsumer()throws Exception{
        ExecutorService fixedServcie = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(100);
        for(int i=0;i<100;i++){
            fixedServcie.execute(()->{

                try{
                    Destination destination = session.createTopic("ZS.SingleTopic");
                    MessageConsumer consumerTemp = session.createConsumer(destination);
                    consumerTemp.setMessageListener((Message message)->{
                        MapMessage mapMessage = (MapMessage)message;
                        try {
                            System.out.println("消费线程，消费   "+Thread.currentThread().getName()+"  name:"+mapMessage.getString("name")+"  age:"+mapMessage.getInt("age"));
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    });
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
                    latch.countDown();
                }catch (Exception e){
                    e.printStackTrace();
                }

            });
        }

        latch.await();

    }

    @Test
    public void testConsumer()throws Exception{

        Stream.of(TopicGenerator.getDestinations(session)).forEach(destination->{
            try {
                consumer = session.createConsumer(destination);
                consumer.setMessageListener((Message message)->{
                    MapMessage mapMessage = (MapMessage)message;
                    try {
                        System.out.println("name:"+mapMessage.getString("name")+"   age:"+mapMessage.getInt("age"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                });
                Thread.sleep(ThreadLocalRandom.current().nextInt(1500));
            } catch (JMSException e) {
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }

        });
    }


    @After
    public void destory()throws Exception{
        if(session != null) {
            session.close();
        }
        if(connection != null) {
            connection.close();
        }
    }

}
