package pers.qianshifengyi.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jms.*;

/**
 * Created by mountain on 2017/8/21.
 */
public class ReqResClientTest {

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
    public void testClient() throws Exception{
        Destination destination = session.createQueue("ZS.REQ_RESQueue");

        Destination tempQueue = session.createTemporaryQueue();

        MessageConsumer responseConsumer = session.createConsumer(tempQueue);

        responseConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                String messageText = null;
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        messageText = textMessage.getText();
                        System.out.println("messageText = " + messageText);
                    }
                } catch (JMSException e) {
                    //Handle the exception appropriately
                    e.printStackTrace();
                }
            }
        });

        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("i am 666");
        textMessage.setJMSReplyTo(tempQueue);
        textMessage.setJMSCorrelationID("ID_111222");

        MessageProducer producer = session.createProducer(destination);
        producer.send(textMessage);

    }

    @After
    public void destroy() throws Exception{
        if(session != null){
            session.close();
        }
        if(connection != null){
            connection.close();
        }
    }

}
