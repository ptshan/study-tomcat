package pers.qianshifengyi.tomcat.exercise2;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.Enumeration;

/**
 * Created by zhangshan on 17/5/3.
 */
public class ServletResourceProcessor2 {
    private Request2 request2;
    private Response2 response2;

    public ServletResourceProcessor2(Request2 request2,Response2 response2){
        this.request2 = request2;
        this.response2 = response2;
    }

    public void process(){
        String uri = request2.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        System.out.println("servletName:"+servletName);
        URL[] urls = new URL[2];
        URLStreamHandler urlStreamHandler = null;
        File classesFile = new File(pers.qianshifengyi.tomcat.util.Constants.WEB_CLASSES);
        File jarFile = new File(pers.qianshifengyi.tomcat.util.Constants.WEB_LIB);

        try {
           // urls[0] = new URL("file",null,classesFile.getCanonicalPath());
           // urls[1] = new URL("file",null,jarFile.getCanonicalPath());
           // urls[0] = new URL("file:/Users/zhangshan193/Documents/projects/git_project/study-tomcat/tomcat-exercise1/src/main/webapps/WEB-INF/classes/");
          //  urls[1] = new URL("file:/Users/zhangshan193/Documents/projects/git_project/study-tomcat/tomcat-exercise1/src/main/webapps/WEB-INF/lib/");

            String repository = (new URL("file", null, classesFile.getCanonicalPath() + File.separator)).toString() ;
            // the code for forming the URL is taken from the addRepository method in
            // org.apache.catalina.loader.StandardClassLoader class.
            urls[0] = new URL(null, repository, urlStreamHandler);

            String repository2 = (new URL("file", null, jarFile.getCanonicalPath() + File.separator)).toString() ;
            // the code for forming the URL is taken from the addRepository method in
            // org.apache.catalina.loader.StandardClassLoader class.
            urls[1] = new URL(null, repository2, urlStreamHandler);
            System.out.println("urls[0]:"+urls[0]);
            System.out.println("urls[1]:"+urls[1]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        URLClassLoader loader = new URLClassLoader(urls);


        try {
            //Class clazz = loader.loadClass("pers/qianshifengyi/tomcat/exercise2/"+servletName+".class");
            Class clazz = loader.loadClass("pers.qianshifengyi.web.app.servlet.TestServlet");
            Servlet rs = (Servlet)clazz.newInstance();
            rs.service(request2,response2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws Exception{
        System.out.println("path:"+ServletResourceProcessor2.class.getClassLoader().getResource(""));
    }

}
