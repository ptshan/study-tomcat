package pers.qianshifengyi.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.qianshifengyi.filter.MyFilter;
import pers.qianshifengyi.listener.MyListener;

import javax.servlet.annotation.WebFilter;

/**
 * ServletRegistrationBean, FilterRegistrationBean and ServletListenerRegistrationBean
 *
 * Created by mountain on 2017/9/3.
 */

@Configuration
public class MyFilterManager {


    @Bean
    public FilterRegistrationBean registration(MyFilter myFilter) {
        System.out.println("---- MyFilterManager   registration before");
        FilterRegistrationBean registration = new FilterRegistrationBean(myFilter);
        System.out.println("---- MyFilterManager   registration after1");
        registration.setEnabled(true);
        System.out.println("---- MyFilterManager   registration after2");

        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean getServletListenerRegistrationBean(MyListener myListener){

        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(myListener);

        return servletListenerRegistrationBean;
    }

}
