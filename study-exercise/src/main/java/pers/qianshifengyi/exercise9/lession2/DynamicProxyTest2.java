package pers.qianshifengyi.exercise9.lession2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhangshan on 17/7/10.
 */
public class DynamicProxyTest2 {

    interface IHello{
        void sayHello();
    }

    static class Hello implements IHello{

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        Object bind(Object originalObj){
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),originalObj.getClass().getInterfaces(),
                    this);
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("--- Welcome ----");
            return method.invoke(originalObj,args);
        }

    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        IHello hello = (IHello)new DynamicProxy().bind(new Hello());
        hello.sayHello();

    }


}
