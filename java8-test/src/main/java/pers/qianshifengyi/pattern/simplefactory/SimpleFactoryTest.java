package pers.qianshifengyi.pattern.simplefactory;

/**
 * Created by mountain on 2017/8/7.
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        IOperator iOperator = OperatorFactory.getOperator("+");
        System.out.println(iOperator.getResult(2,3));

    }

}
