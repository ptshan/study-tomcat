package pers.qianshifengyi.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jms.*;

/**
 * Created by mountain on 2017/8/21.
 */
public class P2PPublisherTest {

    private ActiveMQConnectionFactory factory;

    private Connection connection;

    private Session session;

    @Before
    public void init() throws Exception{

        factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

    }

    @Test
    public void testConnQueue() throws Exception{
        Destination destination = session.createQueue("ZS.TestQueue");
        MessageProducer producer = session.createProducer(null);
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("wa ha ha ,i am zs test queue");
        producer.send(destination,textMessage);

    }






    @After
    public void destroy() throws Exception {
        if(session != null) {
            session.close();
        }
        if(connection != null){
            connection.close();
        }
    }




}
