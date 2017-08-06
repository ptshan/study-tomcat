package pers.qianshifengyi.pattern.strategy;

/**
 * Created by mountain on 2017/8/6.
 */
public class CashContext {

    private ICashSuper iCashSuper;

    public CashContext(ICashSuper iCashSuper){
        this.iCashSuper = iCashSuper;
    }

    public double getResult(double money){
        return iCashSuper.acceptCash(money);
    }

    public CashContext(String type){
        switch (type){
            case "正常收费":
                iCashSuper = new CashNormal();
                break;
            case "满300减去100":
                iCashSuper = new CashReturn(300,100);
                break;
            case "打8折":
                iCashSuper = new CashRebate(0.8);
                break;
        }
    }

}
