package pers.qianshifengyi.tomcat.util;

/**
 * Created by zhangshan on 17/5/2.
 */
public class StringUtils {

    public static boolean isBlank(String str){
        if(str != null || "".equals(str.trim()))
            return true;
        return false;
    }


}
