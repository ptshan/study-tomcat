package pers.qianshifengyi.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jms.*;

/**
 * Created by mountain on 2017/8/21.
 */
public class ReqResServerTest {



    private ActiveMQConnectionFactory factory;

    private Connection connection;

    private Session session;

    //private MessageProtocol messageProtocol;

    @Before
    public void init() throws Exception{
        factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
    }

    @Test
    public void testServer() throws Exception{
        Destination destination = session.createQueue("ZS.REQ_RESQueue");

        MessageConsumer consumer = session.createConsumer(destination);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)message;

                    try {
                        System.out.println("textMessage.getText():"+textMessage.getText());
                        TextMessage response = session.createTextMessage();
                        response.setText(textMessage.getText()+" i came from server");
                        MessageProducer producer = session.createProducer(session.createQueue("ZS.REQ_RESQueue"));

                        producer.send(message.getJMSReplyTo(),response);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }else{
                    System.out.println("----- message not TextMessage");
                }


            }
        });

        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(false);
        brokerService.setPersistent(false);
        brokerService.addConnector("tcp://127.0.0.1:61616");
        brokerService.start();

//        this.session = connection.createSession(this.transacted, ackMode);
//        Destination adminQueue = this.session.createQueue(messageQueueName);
//
//        this.replyProducer = this.session.createProducer(null);
//        this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//
//        MessageConsumer consumer = this.session.createConsumer(adminQueue);
//        consumer.setMessageListener(this);

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
