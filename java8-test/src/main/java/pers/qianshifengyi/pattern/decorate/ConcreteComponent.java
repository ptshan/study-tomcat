package pers.qianshifengyi.pattern.decorate;

/**
 * Created by mountain on 2017/8/7.
 */
public class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("----- concreteComponent");
    }
}
