package pers.qianshifengyi.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jms.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mountain on 2017/8/21.
 */
public class P2PConsumer {

    private ActiveMQConnectionFactory factory;

    private Connection connection;

    private Session session;

    @Before
    public void init() throws Exception {
        factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

    }

    @Test
    public void testConsumer() throws Exception{
        final CountDownLatch latch = new CountDownLatch(1);
        Destination destination = session.createQueue("ZS.TestQueue");
        MessageConsumer consumer = session.createConsumer(destination);
        // consumer.receive()  receive 是同步消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)message;

                    try {
                        System.out.println("textMessage.getText():"+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("----- message not TextMessage");
                }
                latch.countDown();
            }
        });

        latch.await();
    }



    @After
    public void destroy() throws Exception {
        if(session != null){
            session.close();
        }
        if(connection != null){
            connection.close();
        }
    }


}
