package pers.qianshifengyi.pattern.strategy;

/**
 * 满减
 * Created by mountain on 2017/8/6.
 */
public class CashReturn implements ICashSuper {
    private double originalCash;
    private double returnCash;

    /**
     * 例如：满300减去100
     * @param originalCash
     * @param returnCash
     */
    public CashReturn(double originalCash,double returnCash){
        this.originalCash = originalCash;
        this.returnCash = returnCash;
    }

    @Override
    public double acceptCash(double money) {
        if(money < originalCash){
            return money;
        }
        return (money-returnCash);
    }
}
