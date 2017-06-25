package pers.qianshifengyi.exercise7.lession3;

/**
 * 1、静态语句块只能访问定义在静态语句块之前的变量
 * 2、定义在静态语句块之后的变量，在前面的静态语句块可以赋值，但不能访问
 * 验证方式：取消 System.out.println(i); 注释即可
 * Created by mountain on 2017/6/25.
 */
public class Test1 {

    static{
        i = 0;
    //    System.out.println(i);
    }

    static int i = 1;

}
