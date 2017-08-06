package pers.qianshifengyi.pattern.strategy;

/**
 * 打折
 * Created by mountain on 2017/8/6.
 */
public class CashRebate implements ICashSuper {

    private double rebate;

    public CashRebate(double rebate){
        this.rebate = rebate;
    }

    @Override
    public double acceptCash(double money) {
        return money * rebate;
    }
}
