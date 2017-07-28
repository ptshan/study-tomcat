package pers.qianshifengyi.test;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by zhangshan193 on 17/3/14.
 */
public class Test2 {


    public static void main(String[] args) {
        String valueValue = "213.";
        valueValue = valueValue.substring(0,valueValue.indexOf("."));
        System.out.println(valueValue);
    }

    @Test
    public void testString(){
        // first word in a b c d e
        String str = "compositionality is key";
        System.out.println(str.lastIndexOf(" "));
        System.out.println(str.length());
        System.out.println(str.indexOf(" "));
        System.out.println(str.substring(0,str.indexOf(" ")));

    }
}
