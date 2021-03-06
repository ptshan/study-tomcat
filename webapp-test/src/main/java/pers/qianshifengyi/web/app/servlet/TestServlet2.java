package pers.qianshifengyi.web.app.servlet;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhangshan on 17/5/17.
 */
public class TestServlet2 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("i am TestServlet2 ! wa haha ");
        HttpClient httpClient = new DefaultHttpClient();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","777");
        jsonObject.put("msg","I'm TestServlet2");
        PrintWriter pw = servletResponse.getWriter();


        String content = jsonObject.toJSONString();
        //pw.write(jsonObject.toJSONString());
        StringBuilder sb = new StringBuilder();
        //sb.append("HTTP/1.1 200 OK\\r\\nContent-Type: text/html\r\nContent-Length: 22\r\n\r\n<h1>7777 6666 555</h1>");

        sb.append("HTTP/1.1 200 OK\\r\\n")
                //.append("Content-Type: text/plain\r\n")
                .append("Content-Type: text/html\r\n")
                .append("Content-Length: ").append(content.length()).append("\r\n\r\n")
                .append(content);
        pw.write(sb.toString());
        pw.flush();

        System.out.println(sb);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
