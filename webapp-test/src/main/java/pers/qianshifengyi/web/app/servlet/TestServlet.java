package pers.qianshifengyi.web.app.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by zhangshan on 17/5/17.
 */
public class TestServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("i am TestServlet ! wa haha ");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","666");
        jsonObject.put("msg","I'm Strong Strong andi am TestServlet");

        servletResponse.getWriter().write(jsonObject.toJSONString());

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
