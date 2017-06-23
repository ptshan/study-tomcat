package pers.qianshifengyi.tomcat.exercise13;

import org.apache.catalina.Context;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by zhangshan on 17/6/12.
 */
public class ReloadTest implements PropertyChangeListener{

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private boolean reloadable = false;

    public ReloadTest(){
        support.addPropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent event) {
        System.out.println("  propertyChange   ");
        if (event.getPropertyName().equals("reloadable")) {
            try {
                setReloadable( ((Boolean) event.getNewValue()).booleanValue() );
            } catch (NumberFormatException e) {
                System.err.println("webappLoader.reloadable"+event.getNewValue().toString());

            }
        }
    }

    public void setReloadable(boolean reloadable) {
        // Process this property change
        boolean oldReloadable = this.reloadable;
        this.reloadable = reloadable;
        // support 中方法 只有oldValue和newValue不一致才会触发support方法的fire
//        if (oldValue == null || newValue == null || !oldValue.equals(newValue)) {
//            firePropertyChange(new PropertyChangeEvent(this.source, propertyName, oldValue, newValue));
//        }
        System.out.println("oldReloadable:"+oldReloadable+"    this.reloadable:"+this.reloadable);
        support.firePropertyChange("reloadable",
                new Boolean(oldReloadable),
                new Boolean(this.reloadable));


        if (!oldReloadable && this.reloadable)
            System.out.println("threadStart");
        else if (oldReloadable && !this.reloadable)
            System.out.println("------  threadStop  -------");
    }

    public static void main(String[] args) {
        new ReloadTest().setReloadable(true);
    }

}
