package pers.qianshifengyi.pattern.decorate;

/**
 * Created by mountain on 2017/8/7.
 */
public class ConcreteDecoratorA extends Decorator {

    private String state;

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("---- state:"+state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
