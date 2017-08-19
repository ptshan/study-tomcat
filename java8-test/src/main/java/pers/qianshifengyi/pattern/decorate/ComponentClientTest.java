package pers.qianshifengyi.pattern.decorate;

/**
 * Created by mountain on 2017/8/7.
 */
public class ComponentClientTest {

    public static void main(String[] args) {

        Component component = new ConcreteComponent();
        ConcreteDecoratorA concreteDecoratorA = new ConcreteDecoratorA(component);
        concreteDecoratorA.setState("666");

        ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB(concreteDecoratorA);

        concreteDecoratorB.operation();
    }


}
