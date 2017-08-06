package pers.qianshifengyi.pattern.strategy;

/**
 * 策略模式：
 * 是一种定义一系列算法的方法，从概念上来看，所有这些算法完成的都是相同的工作，只是实现不同，它可以以相同的方式调用所有的算法，减少了各种算法类与使用算法类
 * 之间的耦合
 * 策略模式是用来封装算法的，但在实践中，我们可以用它来封装几乎任何类型的规则，只要在分析过程中听到需要在不同时间应用不同的业务规则，就可以考虑用策略模式处理
 * 这种变化的可能性
 * Created by mountain on 2017/8/6.
 */
public class CashClientTest {

    /**
     * 还可以将CashClient中的策略选择放到CashContext中，CashClient通过传入一个String值来进行策略选择调用，
     * 这样就简化了客户端操作
     * CashClient客户端要尽量简单
     * @param args
     */
    public static void main(String[] args) {
        CashContext cashContext = null;
        double result = 0d;

        ICashSuper cashNormal = new CashNormal();
        cashContext = new CashContext(cashNormal);
        result = cashContext.getResult(400);
        System.out.println("normal result:"+result);

        ICashSuper cashRebate = new CashRebate(0.8d);
        cashContext = new CashContext(cashRebate);
        result = cashContext.getResult(400);
        System.out.println("rebate result:"+result);

        ICashSuper cashRetun = new CashReturn(300,100);
        cashContext = new CashContext(cashRetun);
        result = cashContext.getResult(400);
        System.out.println("return result:"+result);

        System.out.println("-------------以下为行为封装在context中--------------------");
        cashContext = new CashContext("正常收费");
        System.out.println("--- result:"+cashContext.getResult(400));
        cashContext = new CashContext("满300减去100");
        System.out.println("--- result:"+cashContext.getResult(400));
        cashContext = new CashContext("打8折");
        System.out.println("--- result:"+cashContext.getResult(400));



    }

}
