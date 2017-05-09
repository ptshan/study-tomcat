package pers.qianshifengyi.tomcat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by zhangshan on 17/5/5.
 */
public final class StringManager {

    private static final Hashtable<String,StringManager> packageTable = new Hashtable<String,StringManager>();

    private Properties properties = new Properties();

    private StringManager(String pkg){

        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().toString();
        String pkgPath = path+pkg.replaceAll("\\.","/");
        try {
            System.out.println(pkgPath+"/LocalStrings.properties");
            File file = new File(pkgPath+"/LocalStrings.properties");
            System.out.println("file.exists():"+file.exists());
            properties.load(new FileInputStream(pkgPath+"/LocalStrings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String pkg="com.pingan";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().toString();
        String pkgPath = path+pkg.replaceAll("\\.","/");
        System.out.println(pkgPath);
    }

    public synchronized static StringManager getStringManager(String pkg){
        StringManager sm = packageTable.get(pkg);
        if(sm == null){
            sm = new StringManager(pkg);
            packageTable.put(pkg,sm);
        }
        return sm;
    }

    public String getString(String key){
        String value = properties.getProperty(key);
        return value;
    }


}
