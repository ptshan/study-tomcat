package pers.qianshifengyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.AbstractEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;

/**
 *
 *
 * 1、无web.xml化
 * 2、无xml配置
 * 3、spring boot 注入bean
 * 4、spring boot 加载properties配置文件
 * 5、spring boot 改用jetty
 * 6、quartz定时任务，启动点在哪，
 *
 * Created by mountain on 2017/8/29.
 */
@Controller
@EnableAutoConfiguration
@ComponentScan("pers.qianshifengyi")
public class SampleJettyController {

    @Value("${zs.username}")
    private String uname;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        System.out.println("uname:"+uname);
        return "Hello World--666!";
    }

    @Bean
    public AbstractEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory ();
        System.out.println("---- do what ?");
        return factory;
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(SampleJettyController.class, args);

    }

}
