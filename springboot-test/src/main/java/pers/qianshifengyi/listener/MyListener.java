package pers.qianshifengyi.listener;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 要让该@WebListener注解生效，需要 在controller之类的地方，加上 @ServletComponentScan("pers.qianshifengyi.listener")
 *
 * 方式1：
 * @WebListener  public class MyListener implements HttpSessionListener
 * @ServletComponentScan("pers.qianshifengyi.listener") 在controller
 *
 * 方式2：@Configuration  public class MyFilterManager {
 * @Component("myListener") public class MyListener implements HttpSessionListener
 * @Bean
 * public ServletListenerRegistrationBean getServletListenerRegistrationBean(MyListener myListener){

 * ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
 * servletListenerRegistrationBean.setListener(myListener);

 * return servletListenerRegistrationBean;
 * }
 *
 * Created by mountain on 2017/9/3.
 */
//@WebListener
    @Component("myListener")
public class MyListener implements HttpSessionListener {



    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("--- MyListener sessionCreated");
        AtomicInteger count = httpSessionEvent.getSession().getAttribute("count") == null ? new AtomicInteger(0):(AtomicInteger)httpSessionEvent.getSession().getAttribute("count");
        count.incrementAndGet();

        httpSessionEvent.getSession().setAttribute("count",count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("--- MyListener sessionDestroyed");
        AtomicInteger count = httpSessionEvent.getSession().getAttribute("count") == null ? new AtomicInteger(0):(AtomicInteger)httpSessionEvent.getSession().getAttribute("count");
        count.decrementAndGet();

        httpSessionEvent.getSession().setAttribute("count",count);
    }
}
