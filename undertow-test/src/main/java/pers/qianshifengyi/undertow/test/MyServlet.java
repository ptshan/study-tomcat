package pers.qianshifengyi.undertow.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mountain on 2017/9/2.
 */
public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 3292116660210354933L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("----- 11111");
        PrintWriter writer = resp.getWriter();
        writer.write("<p style='color:red;text-align:center;'>---***"+this.getInitParameter("message")+"</p>");
        writer.close();
    }
}
