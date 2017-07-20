package pers.qianshifengyi.test;

import org.junit.Test;

/**
 * Created by zhangshan on 17/7/12.
 */
public class StringTest {

    @Test
    public void test1(){
        String str = "abcad";
        int firstCodePoint = str.codePointAt(0);
        System.out.println(firstCodePoint);
        System.out.println(Character.charCount(firstCodePoint));
        System.out.println(Character.charCount(65536));
    }




}
