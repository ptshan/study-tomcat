
Engine > Host > Context > Wrapper

主机(host):如果需要在一个 Tomcat 部署中部署多个上下文,需要使用一个主机。理论上,当只有一个上下文容器的 时候不需要主机,
    但是实践中,一个 Tomcat 部署往往需要一个主机
引擎(Engine):表示整个 Catalina 的 Servlet 引擎,可以添加到引擎上的容器包括 org.apache.catalina.Host 或者 org.apache.catalina.Context。
在一个 Tomcat 部署中,默认的容器是引擎。在 该部署中,引擎只有一个主机,默认主机。


Valve valve = (Valve) Class.forName(errorReportValveClass)
                    .newInstance();
和
classLoader.load(errorReportValveClass).newInstance();
区别

Lifecycle及PropertyChangeSupport相比较:
一、首先,相关的类、接口及所在的包

1、package java.beans;

public class PropertyChangeSupport implements Serializable {
public PropertyChangeSupport(Object sourceBean) {
        if (sourceBean == null) {
            throw new NullPointerException();
        }
        source = sourceBean;
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (oldValue == null || newValue == null || !oldValue.equals(newValue)) {
            firePropertyChange(new PropertyChangeEvent(this.source, propertyName, oldValue, newValue));
        }
    }

    public void firePropertyChange(String propertyName, int oldValue, int newValue) {
        if (oldValue != newValue) {
            firePropertyChange(propertyName, Integer.valueOf(oldValue), Integer.valueOf(newValue));
        }
    }

    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
        if (oldValue != newValue) {
            firePropertyChange(propertyName, Boolean.valueOf(oldValue), Boolean.valueOf(newValue));
        }
    }

    public void firePropertyChange(PropertyChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (oldValue == null || newValue == null || !oldValue.equals(newValue)) {
            String name = event.getPropertyName();

            PropertyChangeListener[] common = this.map.get(null);
            PropertyChangeListener[] named = (name != null)
                        ? this.map.get(name)
                        : null;

            fire(common, event);
            fire(named, event);
        }
    }

    private static void fire(PropertyChangeListener[] listeners, PropertyChangeEvent event) {
        if (listeners != null) {
            for (PropertyChangeListener listener : listeners) {
                listener.propertyChange(event);
            }
        }
    }

    public void fireIndexedPropertyChange(String propertyName, int index, Object oldValue, Object newValue) {
        if (oldValue == null || newValue == null || !oldValue.equals(newValue)) {
            firePropertyChange(new IndexedPropertyChangeEvent(source, propertyName, oldValue, newValue, index));
        }
    }

    public void fireIndexedPropertyChange(String propertyName, int index, int oldValue, int newValue) {
        if (oldValue != newValue) {
            fireIndexedPropertyChange(propertyName, index, Integer.valueOf(oldValue), Integer.valueOf(newValue));
        }
    }

    public void fireIndexedPropertyChange(String propertyName, int index, boolean oldValue, boolean newValue) {
        if (oldValue != newValue) {
            fireIndexedPropertyChange(propertyName, index, Boolean.valueOf(oldValue), Boolean.valueOf(newValue));
        }
    }
    .
    .
    .
}
2、package java.util;
EventListener只是一个接口监听器的空实现
public interface EventListener {
}
package java.beans;
public interface PropertyChangeListener extends java.util.EventListener {

    void propertyChange(PropertyChangeEvent evt);

}
3、package java.util;
public class EventObject implements java.io.Serializable {

    private static final long serialVersionUID = 5516075349620653480L;

    protected transient Object  source;

    public EventObject(Object source) {
        if (source == null)
            throw new IllegalArgumentException("null source");

        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public String toString() {
        return getClass().getName() + "[source=" + source + "]";
    }
}

package java.beans;
public class PropertyChangeEvent extends EventObject {
public PropertyChangeEvent(Object source, String propertyName,
                               Object oldValue, Object newValue) {
        super(source);
        this.propertyName = propertyName;
        this.newValue = newValue;
        this.oldValue = oldValue;
    }
    .
    .
    .
}
4、package java.util;
public abstract class EventListenerProxy<T extends EventListener>
        implements EventListener {

    private final T listener;

    public EventListenerProxy(T listener) {
        this.listener = listener;
    }

    public T getListener() {
        return this.listener;
    }
}
5、package java.beans;
public class PropertyChangeListenerProxy extends EventListenerProxy<PropertyChangeListener>
                                         implements PropertyChangeListener {

    private final String propertyName;

    public PropertyChangeListenerProxy(String propertyName, PropertyChangeListener listener) {
        super(listener);
        this.propertyName = propertyName;
    }

    public void propertyChange(PropertyChangeEvent event) {
        getListener().propertyChange(event);
    }

    public String getPropertyName() {
        return this.propertyName;
    }
}
6、package java.beans;
abstract class ChangeListenerMap<L extends EventListener> {
    private Map<String, L[]> map;
    .
    .
    .
}






































