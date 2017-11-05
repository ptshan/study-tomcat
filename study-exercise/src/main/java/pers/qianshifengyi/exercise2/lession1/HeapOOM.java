package pers.qianshifengyi.exercise2.lession1;

import pers.qianshifengyi.exercise2.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * Created by zhangshan on 17/6/29.
 */
public class HeapOOM {

    /**
     * java.lang.OutOfMemoryError: Java heap space
     Dumping heap to java_pid98510.hprof ...
     Heap dump file created [33253117 bytes in 0.590 secs]
     Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     at java.util.Arrays.copyOf(Arrays.java:3210)
     at java.util.Arrays.copyOf(Arrays.java:3181)
     at java.util.ArrayList.grow(ArrayList.java:261)
     at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
     at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
     at java.util.ArrayList.add(ArrayList.java:458)
     at pers.qianshifengyi.exercise2.lesson1.HeapOOM.main(HeapOOM.java:19)
     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     at java.lang.reflect.Method.invoke(Method.java:497)
     at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
     * @param args
     */
    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>();
        int i = 0;
        // 360145 溢出
        while(true){
            System.out.println(i++);
            userList.add(new User());
        }

    }

}
