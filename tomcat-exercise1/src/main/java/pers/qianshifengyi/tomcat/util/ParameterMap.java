package pers.qianshifengyi.tomcat.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangshan on 17/5/11.
 */
public final class ParameterMap extends HashMap {

    private StringManager sm = StringManager.getStringManager("pers.qianshifengyi.tomcat.util");

    private boolean locked = true;

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public ParameterMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ParameterMap(int initialCapacity) {
        super(initialCapacity);
    }

    public ParameterMap() {
        super();
    }

    public ParameterMap(Map m) {
        super(m);
    }

    @Override
    public Object put(Object key, Object value) {
        if(locked == true){
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }
        return super.put(key, value);
    }

    @Override
    public void putAll(Map m) {
        if(locked == true){
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }
        super.putAll(m);
    }

    @Override
    public Object remove(Object key) {
        if(locked == true){
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }
        return super.remove(key);
    }

    @Override
    public void clear() {
        if(locked == true){
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }
        super.clear();
    }
}
