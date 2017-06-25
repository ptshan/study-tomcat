package pers.qianshifengyi.exercise7.lession2;

/**
 * Created by mountain on 2017/6/25.
 */
public class FieldResolution {

    interface Interface0{
        int A = 0;
    }

    interface Interface1 extends Interface0{
        int A = 1;
    }

    interface Interface2{
        int A = 2;
    }

    static class Parent implements Interface1{
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2{
        public static int A = 4;
    }

    /**
     * 字段解析：(2和3并行执行，没有先后吧，验证方式是注释掉  public static int A = 4; 看main的报错信息)
     * 1、首先在本类中查找
     * 2、其次在接口和父接口中查找
     * 3、再次在父类中查找
     * 4、没找到抛异常
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Sub.A);
    }

}
