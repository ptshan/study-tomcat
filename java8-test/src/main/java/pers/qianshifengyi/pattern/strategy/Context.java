package pers.qianshifengyi.pattern.strategy;

/**
 * Created by mountain on 2017/8/6.
 */
public class Context {

    private IStrategy iStrategy;

    public Context(IStrategy iStrategy){
        this.iStrategy = iStrategy;
    }

    public void contextInterface(){
        iStrategy.algorithmInterface();
    }

}
