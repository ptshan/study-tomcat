package pers.qianshifengyi.tomcat.exercise19;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

public class Test {

     public static void main(String[] args)throws Exception {
     //  String jarPath = "/Users/zhangshan193/Downloads/lib_test";
     String jarPath = "/Users/zhangshan193/Downloads/lib_test/ansirQa.jar";
     String classPath = "/Users/zhangshan193/Documents/projects/git_project/study-tomcat/tomcat-exercise1/src/main/java/";
     File jarFile = new File(jarPath);
             File classFile = new File(classPath);

     ClassLoader classLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL(),classFile.toURI().toURL()});


         System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径
         System.out.println(System.getProperty("user.dir"));//用户的当前路径
         System.out.println("------------------------------------");

     Class test2Class = classLoader.loadClass("pers.qianshifengyi.tomcat.exercise19.Test2");
         Class convertUtilsClass = classLoader.loadClass("com.pingan.qhzx.anshao.model.dto.ansirqa.DicDesc");
         System.out.println("convertUtilsClass.getClassLoader():"+convertUtilsClass.getClassLoader());
         System.out.println("111 test2Class.getClassLoader():"+test2Class.getClassLoader());
         System.out.println("111 Test.class.getClassLoader():"+Test.class.getClassLoader());

         System.out.println("-------------------------------------");
         Class helloTomcatClass = classLoader.loadClass("pers.qianshifengyi.tomcat.HelloTomcat");
         System.out.println("---- helloTomcatClass:"+helloTomcatClass.getClassLoader());

         Method setClassLoaderMethod = test2Class.getMethod("setClassLoader",ClassLoader.class);
         Object test2Obj = test2Class.newInstance();
         setClassLoaderMethod.invoke(test2Obj,classLoader);



     }


}
