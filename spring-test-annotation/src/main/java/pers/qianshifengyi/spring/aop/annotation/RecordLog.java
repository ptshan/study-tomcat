package pers.qianshifengyi.spring.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 正常执行顺序 1、around start 2、before 3、业务service  4、around的pjp.proceed() 5、around end  6、after 7、afterReturning
 * --myAround start
 * --myBeforeLog
 * UserServiceImpl selectAll 执行
 * result:[User{id=1, userName='张 1 喵', age=28},...]
 * --myAround end
 * --myAfterLog
 * --myAfterReturning myRetVal:[User{id=1, userName='张 1 喵', age=28},...]
 *
 * 若业务service抛出异常，则执行顺序为：1、around start  2、before  3、业务service挂了  4、after   5、afterThrowing
 * --myAround start
 * --myBeforeLog
 * --myAfterLog
 * --myAfterThrowing
 *
 *
 * Created by mountain on 2017/8/26.
 */
@Component
@Aspect
public class RecordLog {

    @Pointcut("execution(public * pers.qianshifengyi.service..*.*(..))")
    public void myLog(){

    }

    @Before("myLog()")
    public void myBeforeLog(){
        System.out.println("--myBeforeLog");
    }

    @After("myLog()" )
    public void myAfterLog(){
        System.out.println("--myAfterLog");
    }

    @AfterThrowing("myLog()")
    public void myAfterThrowing(){
        System.out.println("--myAfterThrowing");
    }

    @AfterReturning(pointcut = "myLog()",returning="myRetVal")
    public void myAfterReturning(Object myRetVal){
        System.out.println("--myAfterReturning myRetVal:"+myRetVal);
    }

    @Around("myLog()")
    public Object myAround(ProceedingJoinPoint pjp)throws Throwable{
        System.out.println("--myAround start");
        Object result = pjp.proceed();
        System.out.println("result:"+result);
        System.out.println("--myAround end");
        return result;
    }



}
