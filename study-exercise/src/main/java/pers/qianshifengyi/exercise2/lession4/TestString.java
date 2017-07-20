package pers.qianshifengyi.exercise2.lession4;

/**
 * 字符串讲解: http://www.cnblogs.com/javaminer/p/3923484.html
 * 字符串常用方式:
 * （1）通过new方式如String s = new String("iByteCode")及string.intern()方法
 *
 * （2）通过字面量的形式如String s = "aaaaa"
 *
 * （3）字面量+字面量如String s = "bbbb" + "ccccc"
 *
 * （4）字面量+变量如String s1 = "dddd";String s = "eeeee"+s1
 *
 *
 * Created by zhangshan on 17/6/29.
 */
public class TestString {

    public static void main(String[] args) {
        // 返回true
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);


//        String str2 = new StringBuilder("come").append("onbaby").toString();
//        System.out.println(str2.intern() == str2);

        String str3 = new StringBuilder("comeon").append("baby").toString();
        System.out.println(str3.intern() == str3);

//        String str4 = new StringBuilder("comeon").append("babyone").toString();
//        System.out.println(str4.intern() == str4);

    }



}
