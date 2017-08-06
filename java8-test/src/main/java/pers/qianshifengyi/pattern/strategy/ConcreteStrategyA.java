package pers.qianshifengyi.pattern.strategy;

/**
 * Created by mountain on 2017/8/6.
 */
public class ConcreteStrategyA implements IStrategy {


    @Override
    public void algorithmInterface() {
        System.out.println("--- ConcreteStrategyA algorithmInterface execute");
    }
}
