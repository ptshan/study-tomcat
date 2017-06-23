package pers.qianshifengyi.tomcat.exercise13;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by zhangshan on 17/6/9.
 */
public class NumberCount implements PropertyChangeListener{

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    long num = 0;

    public NumberCount(){
        support.addPropertyChangeListener(this);
    }

    public void count(){
        //while(true) {
            long oldValue = num;
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num++;
            support.firePropertyChange("countChanged", oldValue, num);
       // }
    }

    public static void main(String[] args) {
        new NumberCount().count();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("值变化了,propertyName:"+evt.getPropertyName()+"    oldValue:"+evt.getOldValue()+"    newValue:"+evt.getNewValue());

        long oldValue = (Long)evt.getOldValue();
        long newValue = (Long)evt.getNewValue();
        support.firePropertyChange("countChanged", oldValue, newValue);

    }

}
