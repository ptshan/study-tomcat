package pers.qianshifengyi.tomcat.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Created by zhangshan on 17/5/11.
 */
public class Enumerator<T> implements Enumeration<T> {

    private Iterator<T> it;

    public Enumerator(Collection<T> collection){
        it = collection.iterator();
    }

    @Override
    public boolean hasMoreElements() {
        return it.hasNext();
    }

    @Override
    public T nextElement() {
        return it.next();
    }
}
