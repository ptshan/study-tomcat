package pers.qianshifengyi.pattern.decorate;

/**
 * Created by mountain on 2017/8/7.
 */
public class ConcreteDecoratorB extends Decorator {



    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        userBehavior();
    }

    public void userBehavior(){
        System.out.println("---- user behavior ------");
    }
}
