package pers.qianshifengyi.tomcat.exercise4;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by zhangshan on 17/5/11.
 */
public class Bootstrap4 {

    public static void main(String[] args) {

        Stack<String> stack = new Stack();
        stack.setSize(4);

        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.push("6");
        stack.push("7");
        stack.push("8");
        stack.push("9");
        stack.push("10");
        stack.push("11");
        stack.push("12");
        stack.push("13");
        stack.push("14");

        System.out.println(stack.size());
        System.out.println(stack.capacity());
        System.out.println("-------------");

        for(String s:stack){
            System.out.println(s);
        }


    }


}
