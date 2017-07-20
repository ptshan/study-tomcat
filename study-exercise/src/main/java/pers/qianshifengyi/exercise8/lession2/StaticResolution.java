package pers.qianshifengyi.exercise8.lession2;

import java.io.Serializable;

/**
 * Created by zhangshan on 17/7/7.
 */
public class StaticResolution {

    public static void sayHello(){
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
        Character character = null;
        char c = 'a';
        say2('b');
    }

    public static void say2(Serializable m){
        System.out.println(m);
    }

}
