package pers.qianshifengyi.exercise8.lession3;

import java.io.Serializable;

/**
 * 转型类型: char int long float double  Character Serializable Object
 * Created by zhangshan on 17/7/7.
 */
public class TestOverload {

//    public static void sayHello(char arg){
//        System.out.println("hello char");
//    }
//    public static void sayHello(int arg){
//        System.out.println("hello int");
//    }
//    public static void sayHello(long arg){
//        System.out.println("hello long");
//    }
//    public static void sayHello(Character arg){
//        System.out.println("hello Character");
//    }
//    public static void sayHello(Serializable arg){
//        System.out.println("hello Serializable");
//    }
    public static void sayHello(Object arg){
        System.out.println("hello Object");
    }

    public static void main(String[] args) {
        TestOverload.sayHello('a');
    }



}
