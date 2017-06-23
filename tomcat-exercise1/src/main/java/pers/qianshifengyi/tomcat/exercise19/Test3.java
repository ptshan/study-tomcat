package pers.qianshifengyi.tomcat.exercise19;

import pers.qianshifengyi.tomcat.util.Constants;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.Enumeration;

/**
 * Created by zhangshan on 17/6/16.
 */
public class Test3 {

    public static void main(String[] args)throws Exception {
        String jarPath = "/Users/zhangshan193/Downloads/lib_test/";
        File jarFile = new File(jarPath);
        System.out.println(jarFile.toURI().toURL());
        String repository1 = (new URL("file", null, jarFile.getCanonicalPath() + File.separator)).toString();
        System.out.println("repository1:"+repository1);
        URLStreamHandler urlStreamHandler = null;
        URL[] urlArray = new URL[1];
        urlArray[0] = new URL(null, repository1, urlStreamHandler);

        ClassLoader classLoader = new URLClassLoader(urlArray);

        Enumeration<URL> urls = classLoader.getResources("");
        System.out.println("111-----------start----------");
        while(urls.hasMoreElements()){
            System.out.println(urls.nextElement());
        }
        System.out.println("666:"+classLoader.getResource("ansirQa.jar"));
        System.out.println("111-----------stop----------");
        Class convertUtilsClass = classLoader.loadClass("com.pingan.qhzx.anshao.model.dto.ansirqa.DicDesc");



    }


}
