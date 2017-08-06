package pers.qianshifengyi.pattern.strategy;

/**
 * 正常收费
 * Created by mountain on 2017/8/6.
 */
public class CashNormal implements ICashSuper {

    @Override
    public double acceptCash(double money) {
        return money;
    }
}
