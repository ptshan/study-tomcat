package pers.qianshifengyi.tomcat.exercise19;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.Enumeration;

import com.pingan.qhzx.anshao.model.dto.ansirqa.DicDesc;

/**
 * Created by zhangshan on 17/6/16.
 */
public class Test2 {

    static {

    }

    private ClassLoader classLoader ;

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;

        System.out.println("test2 classLoader:"+classLoader);
        System.out.println("Test2.class.getClassLoader():"+Test2.class.getClassLoader());

        System.out.println("Test2.class.getClassLoader().getResource(\"\"):"+Test2.class.getClassLoader().getResource(""));

        Class convertUtilsClass = null;
        try {
            convertUtilsClass =classLoader.loadClass("com.pingan.qhzx.anshao.model.dto.ansirqa.DicDesc");
            System.out.println("convertUtilsClass:"+convertUtilsClass);
            System.out.println("convertUtilsClass.newInstance():"+convertUtilsClass.newInstance());
            classLoader.getResource("ansirQa.jar");
            Enumeration<URL> urls = classLoader.getResources("");
            System.out.println("222-----------start----------");
            while(urls.hasMoreElements()){
                System.out.println(urls.nextElement());
            }
            System.out.println("222-----------stop----------");
            // 防止  AppClassLoader 加载 Test2.class ,调用子加载器 URLClassLoader, 父加载器不可以加载子加载器的类
            // 但是子加载器可以调用父加载器加载的类
            DicDesc dicDesc = (DicDesc)convertUtilsClass.newInstance();
            System.out.println("--- disDesc:"+dicDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testSubstring()throws Exception{
        String url = "jar:aaa/bbb/ccc.jar";
        if (url.startsWith("jar:")) {
            url = url.substring(4, url.length() - 2);
        }
        System.out.println(url);
        url = "file://c:/bb/cc";
        if (url.startsWith("file://"))
            System.out.println(url.substring(7));
        url="file:c:/bb/cc";
        if (url.startsWith("file:"))
            System.out.println(url.substring(5));
    }

    public static void readJar2() throws  Exception {
        String jarPath = "/Users/zhangshan193/Documents/projects/git_project/study-tomcat/lib_local/commons-beanutils.jar";
        File jarFile = new File(jarPath);
        // file:/Users/zhangshan193/Documents/projects/git_project/study-tomcat/lib_local/commons-beanutils.jar
        System.out.println(jarFile.toURL());
        URLStreamHandler handler = null;
        URL url = new URL(null,"jar:"+jarFile.toURL().toString()+"!/overview.html",handler);
        // file:/Users/zhangshan193/Documents/projects/git_project/study-tomcat/lib_local/commons-beanutils.jar
        System.out.println(url);
        System.out.println("---666---");

        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        while(line != null){
            System.out.println(line);
            line = br.readLine();
        }
        is.close();

    }

}
