package pers.qianshifengyi.exercise9.lession3;

/**
 * Created by zhangshan on 17/7/10.
 */
public class HotSwapClassLoader extends ClassLoader {

    /**
     * 设置HotSwapClassLoader的父加载器为HotSwapClassLoader类的加载器
     */
    protected HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    /**
     * 将 protected 的 defineClass 包装成public的,便于通过字节数组来实例化类
     * @param classBytes
     * @return
     */
    public Class loadBytes(byte[] classBytes){
        return defineClass(null,classBytes,0,classBytes.length);
    }




}
