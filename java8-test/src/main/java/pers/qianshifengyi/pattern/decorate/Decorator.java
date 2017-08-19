package pers.qianshifengyi.pattern.decorate;

/**
 * Created by mountain on 2017/8/7.
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void operation() {
        if(component != null){
            component.operation();
        }
    }
}
