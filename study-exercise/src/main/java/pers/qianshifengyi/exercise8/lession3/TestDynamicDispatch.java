package pers.qianshifengyi.exercise8.lession3;

/**
 * Created by zhangshan on 17/7/7.
 */
public class TestDynamicDispatch {

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();

        man = new Woman();
        man.sayHello();
    }

}
