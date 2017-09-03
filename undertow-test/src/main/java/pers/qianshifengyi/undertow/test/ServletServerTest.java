package pers.qianshifengyi.undertow.test;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletContainer;
import io.undertow.servlet.api.ServletInfo;

/**
 * http://localhost:8081/myapp/myServlet
 * Undertow 是红帽公司（RedHat）的开源产品，是 WildFly8（JBoos） 默认的 Web 服务器。
 * Undertow是一个用java编写的灵活的高性能Web服务器，提供基于NIO的阻塞和非阻塞API。
 * 官网API总结特点：
 *
 * Lightweight（轻量级）
 *
 * Undertow非常轻量级，Undertow核心jar包在1Mb以下。 它在运行时也是轻量级的，有一个简单的嵌入式服务器使用少于4Mb的堆空间
 *
 * HTTP Upgrade Support（支持http升级）
 *
 * 支持HTTP升级，允许多个协议通过HTTP端口进行多路复用
 *
 * Web Socket Support（支持WebScoket）
 *
 * Undertow提供对Web Socket的全面支持，包括JSR-356支持
 *
 * Servlet 3.1
 *
 * Undertow提供对Servlet 3.1的支持，包括对嵌入式servlet的支持。 还可以在同一部署中混合Servlet和本机Undertow非阻塞处理程序
 *
 * Embeddable（可嵌入的）
 *
 * Undertow可以嵌入在应用程序中或独立运行，只需几行代码
 *
 * 6. Flexible（灵活性）
 *
 * Undertow框架jar包： undertow-core.jar undertow-servlet.jar
 *
 * Created by mountain on 2017/9/2.
 */
public class ServletServerTest {

    public static void main(String[] args)throws Exception {

        ServletInfo servletInfo1 = Servlets.servlet("MyServlet",MyServlet.class);
        servletInfo1.addInitParam("message","This is my first MyServlet!");
        servletInfo1.addMapping("/myServlet");

        DeploymentInfo deploymentInfo1 = Servlets.deployment();
        deploymentInfo1.setClassLoader(ServletServerTest.class.getClassLoader());
        deploymentInfo1.setContextPath("/myapp");
        deploymentInfo1.setDeploymentName("myServlet.war");

        deploymentInfo1.addServlet(servletInfo1);

        ServletContainer container = Servlets.defaultContainer();
        DeploymentManager manager = container.addDeployment(deploymentInfo1);
        manager.deploy();
        // 路径处理器
        PathHandler pathHandler = Handlers.path();
        HttpHandler myApp = null;
        // 启动容器
        myApp = manager.start();
        // 绑定关系
        pathHandler.addPrefixPath("/myapp",myApp);

        Undertow server = Undertow.builder().addHttpListener(8081,"localhost")
                .setHandler(pathHandler).build();// 设置分发处理器

        server.start();
    }


}
