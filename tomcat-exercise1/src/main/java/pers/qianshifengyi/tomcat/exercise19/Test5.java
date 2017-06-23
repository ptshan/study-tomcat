package pers.qianshifengyi.tomcat.exercise19;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * Created by zhangshan on 17/6/19.
 */
public class Test5 {

    public static void main(String[] args) throws Exception{
        String jarPath = "/Users/zhangshan193/Downloads/lib_test/ansirQa.jar";
        String classPath = "/Users/zhangshan193/Documents/projects/git_project/study-tomcat/tomcat-exercise1/src/main/java/";
        File jarFile = new File(jarPath);
        File classFile = new File(classPath);

        ClassLoader classLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL(),classFile.toURI().toURL()});

        Class test2Class = classLoader.loadClass("pers.qianshifengyi.tomcat.exercise19.Test2");
        Class convertUtilsClass = classLoader.loadClass("com.pingan.qhzx.anshao.model.dto.ansirqa.DicDesc");
        System.out.println("test2Class.getClassLoader():"+test2Class.getClassLoader());
        System.out.println("convertUtilsClass.getClassLoader():"+convertUtilsClass.getClassLoader());

    }



}
