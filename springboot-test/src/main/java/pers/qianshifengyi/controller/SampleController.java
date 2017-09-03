package pers.qianshifengyi.controller;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.qianshifengyi.common.ZSConfig;
import pers.qianshifengyi.filter.MyFilter;
import pers.qianshifengyi.listener.MyListener;
import pers.qianshifengyi.service.UserService;
import org.apache.catalina.Context;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

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
//@ServletComponentScan("pers.qianshifengyi.listener")
public class SampleController {

    @Resource
    private UserService userService;

    @Value("${zs.username}")
    private String uname;

    @Autowired
    private ZSConfig zsConfig;

    @Autowired
    private MyFilter myFilter;

//    @Autowired
//    private MyListener myListener;

    @RequestMapping("/")
    @ResponseBody
    String home(HttpSession httpSession,HttpServletRequest req) {
        System.out.println(userService.selectById(5));
        System.out.println("uname:"+uname);
        System.out.println("zsConfig.getServers():"+zsConfig.getServers());
        System.out.println("myFilter:"+myFilter);
//        System.out.println("myListener:"+myListener);
        System.out.println("httpSession:"+httpSession);
        System.out.println("httpSession.getAttribute(\"count\"):"+httpSession.getAttribute("count"));
        System.out.println("req.getSession().getAttribute(\"count\"):"+req.getSession().getAttribute("count"));

        return "Hello World!";
    }

    /**
     * 在application.properties中配置的
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        /**
         * UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
         factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {

        @Override
        public void customize(Builder builder) {
        builder.addHttpListener(8080, "0.0.0.0");
        }

        });
         */
        // JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        tomcatFactory.setPort(8082);
        tomcatFactory.setContextPath("/aaa");
        return tomcatFactory;
    }

    /*
    @Bean
    public EmbeddedServletContainerCustomizer cookieProcessorCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                if (container instanceof TomcatEmbeddedServletContainerFactory) {
                    ((TomcatEmbeddedServletContainerFactory) container)
                            .addContextCustomizers(new TomcatContextCustomizer() {

                                @Override
                                public void customize(Context context) {
                                    context.setCookieProcessor(new LegacyCookieProcessor());
                                }

                            });
                }
            }

        };
    }



    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            File keystore = new ClassPathResource("keystore").getFile();
            File truststore = new ClassPathResource("keystore").getFile();
            connector.setScheme("https");
            connector.setSecure(true);
            connector.setPort(8443);
            protocol.setSSLEnabled(true);
            protocol.setKeystoreFile(keystore.getAbsolutePath());
            protocol.setKeystorePass("changeit");
            protocol.setTruststoreFile(truststore.getAbsolutePath());
            protocol.setTruststorePass("changeit");
            protocol.setKeyAlias("apitester");
            return connector;
        }
        catch (IOException ex) {
            throw new IllegalStateException("can't access keystore: [" + "keystore"
                    + "] or truststore: [" + "keystore" + "]", ex);
        }
    }
*/
    public static void main(String[] args) throws Exception {

        SpringApplication.run(SampleController.class, args);

    }

}
