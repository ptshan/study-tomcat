package pers.qianshifengyi.pattern.simplefactory;

/**
 * Created by mountain on 2017/8/7.
 */
public class DivOperator implements IOperator {

    @Override
    public double getResult(double num1, double num2) {
        return num1/num2;
    }

}
