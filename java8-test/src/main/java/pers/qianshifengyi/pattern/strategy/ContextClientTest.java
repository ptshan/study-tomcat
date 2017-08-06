package pers.qianshifengyi.pattern.strategy;

/**
 * Created by mountain on 2017/8/6.
 */
public class ContextClientTest {

    public static void main(String[] args) {
        Context context = null;

        IStrategy concreteStrategyA = new ConcreteStrategyA();
        context = new Context(concreteStrategyA);
        context.contextInterface();

        IStrategy concreteStrategyB = new ConcreteStrategyB();
        context = new Context(concreteStrategyB);
        context.contextInterface();

        IStrategy concreteStrategyC = new ConcreteStrategyC();
        context = new Context(concreteStrategyC);
        context.contextInterface();

    }


}
