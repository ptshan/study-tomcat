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
public class Test4 {

    public static void main(String[] args)throws Exception {
        URL[] urls = new URL[1];
        URLStreamHandler urlStreamHandler = null;
        File libFile = new File(Constants.WEB_LIB);

        String repository0 = (new URL("file", null, libFile.getCanonicalPath() + File.separator)).toString();
        urls[0] = new URL(null, repository0, urlStreamHandler);

        URLClassLoader classLoader = new URLClassLoader(urls);
        Enumeration<URL> urlEnumeration = classLoader.getResources("");
        System.out.println("111-----------start----------");
        while(urlEnumeration.hasMoreElements()){
            System.out.println(urlEnumeration.nextElement());
        }
        // 类加载器必须指定jar文件,而不是jar的目录来加载jar文件
        Class jsonObjectClass = classLoader.loadClass("com.alibaba.fastjson.JSONObject");
        System.out.println("jsonObjectClass.getClassLoader():"+jsonObjectClass.getClassLoader());
    //    Class jsonObjectClass = classLoader.loadClass("org.apache.http.impl.client.DefaultHttpClient");
        System.out.println("jsonObjectClass:"+jsonObjectClass);
    }



}
