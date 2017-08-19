package pers.qianshifengyi.pattern.simplefactory;

/**
 * Created by mountain on 2017/8/7.
 */
public class OperatorFactory {

    public static IOperator getOperator(String type){
        IOperator iOperator = null;
        switch (type){
            case "+":
                iOperator = new AddOperator();
                break;
            case "-":
                iOperator = new SubOperator();
                break;
            case "*":
                iOperator = new MulOperator();
                break;
            case "/":
                iOperator = new DivOperator();
                break;
            default:
                System.out.println("出现不支持的操作符 type:"+type);
                break;
        }
        return iOperator;
    }


}
