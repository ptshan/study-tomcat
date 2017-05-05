package pers.qianshifengyi.tomcat.exercise2;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by zhangshan on 17/5/3.
 */
public class ResourceServlet implements Servlet{


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("当前ResourceServlet类加载器路径:"+ResourceServlet.class.getClassLoader().getResource(""));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","0000");
        jsonObject.put("msg","success");
        ((Response2)servletResponse).getOs().write(jsonObject.toJSONString().getBytes());

//        servletResponse.getWriter().write(jsonObject.toJSONString());
//        servletResponse.getWriter().flush();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
