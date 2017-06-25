package pers.qianshifengyi.exercise7.lession4;

/**
 * 由于虚拟机会保证在子类构造器clinit被执行之前，父类类构造器clinit已经执行，而clinit是编译器自动收集类中所有类变量
 * 的赋值动作和静态语句块中语句合并产生的，所以
 * 1、在执行Sub类的Clinit之前，先执行Parent的clinit，
 * 2、Parent的clinit收集其类变量A和静态语句块的语句，合并后，A = 2；
 * 3、父类Parent的clinit执行过后，子类Sub收集其类变量B，没有静态语句块，这时A=2，所以B=A=2;
 * Created by mountain on 2017/6/25.
 */
public class TestClinitIndex {

    static class Parent{
        public static int A = 1;
        static{
            A = 2;
        }
    }

    static class Sub extends Parent{
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }








}
