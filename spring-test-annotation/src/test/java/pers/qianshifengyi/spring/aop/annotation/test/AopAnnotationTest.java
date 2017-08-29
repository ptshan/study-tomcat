package pers.qianshifengyi.spring.aop.annotation.test;

import org.aspectj.lang.annotation.Aspect;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import pers.qianshifengyi.entity.User;
import pers.qianshifengyi.service.UserService;

import java.util.List;

/**
 * Created by mountain on 2017/8/26.
 */


public class AopAnnotationTest {

    @Test
    public void testBeans(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        System.out.println("userService:"+userService);
        List<User> userList = userService.selectAll();
        userList.forEach(System.out::println);
    }


}


