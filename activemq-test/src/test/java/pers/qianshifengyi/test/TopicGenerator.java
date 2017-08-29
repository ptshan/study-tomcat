package pers.qianshifengyi.test;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import java.util.Arrays;

/**
 * Created by mountain on 2017/8/20.
 */
public class TopicGenerator {

    private static Destination[] destinations = new Destination[10000];

    public static Destination[] getDestinations(final Session session){
        if(destinations[0] == null) {
            Arrays.setAll(destinations, i -> {
                Destination destination = null;
                try {
                    return session.createTopic("ZS.Topic" + i);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                return destination;
            });
        }
        return destinations;
    }



}
