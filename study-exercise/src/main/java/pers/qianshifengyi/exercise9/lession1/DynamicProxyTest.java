package pers.qianshifengyi.exercise9.lession1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhangshan on 17/7/10.
 */
public class DynamicProxyTest {

    public static void main(String[] args) throws Exception{
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        IHello iHello = (IHello)(Proxy.newProxyInstance(Hello.class.getClassLoader(),
                Hello.class.getInterfaces(),new DynamicProxy(new Hello())));
        iHello.sayHello();
    }

}

interface IHello{
    void sayHello();
}

class Hello implements IHello{
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}

class DynamicProxy implements InvocationHandler{

    Object originalObj;

    public DynamicProxy(Object originalObj){
        this.originalObj = originalObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Welcome");

        Object resultObj = method.invoke(originalObj,args);
        return resultObj;
    }
}

