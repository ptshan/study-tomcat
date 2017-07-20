package pers.qianshifengyi.exercise9.lession3;

import sun.security.util.SecurityConstants;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;

/**
 * 目的是将要动态加载的类的System.out和System.err重新定位到HackSystem的输出流中,仅
 * 重定位System.out和System.err两种,其余的不变
 * Created by zhangshan on 17/7/10.
 */
public class HackSystem {

    /**
     * 将HackSystem.in定位到System.in
     */
    public final static InputStream in = System.in;

    private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    /**
     * out是往ByteArrayOutputStream中输入
     */
    public final static PrintStream out = new PrintStream(buffer);

    /**
     * 将err定位成和out一样
     */
    public final static PrintStream err = out;

    /**
     * 将ByteArrayOutputStream中缓存的数据转成String进行返回
     * @return
     */
    public static String getBufferString(){
        return buffer.toString();
    }

    /**
     * 将缓存进行清空
     */
    public static void clearBuffer(){
        buffer.reset();
    }

    /**
     * 以下均采用System来实现
     * @param s
     */
    public static void setSecurityManager(final SecurityManager s) {
        System.setSecurityManager(s);
    }

    public static SecurityManager getSecurityManager() {
        return System.getSecurityManager();
    }

    public static long currentTimeMillis(){
        return System.currentTimeMillis();
    }

    public static long nanoTime(){
        return System.nanoTime();
    }

    public static void arraycopy(Object src,  int  srcPos,Object dest, int destPos,int length){
        System.arraycopy(src,srcPos,dest,destPos,length);
    }

    public static int identityHashCode(Object x){
        return System.identityHashCode(x);
    }

}
