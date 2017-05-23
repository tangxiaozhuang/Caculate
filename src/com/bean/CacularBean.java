package com.bean;

import java.util.Date;

/**
 * Created by thz on 2017/4/25.
 */
public class CacularBean {

    //开始的日期
    private static Date dateBg;
    //借出金额
    private double LMoney;
    //借出期限
    private int LDate;
    //借出利率
    private double LRate;
    //期限类型
    private int DateType;
    //利率类型
    private int RateType;
    //还款方式
    private int repayType;

    public static Date getDateBg() {
        return dateBg;
    }

    public void setDateBg(Date dateBg) {
        this.dateBg = dateBg;

    }

    public double getLMoney() {
        return LMoney;
    }

    public void setLMoney(double LMoney) {
        this.LMoney = LMoney;
    }

    public int getLDate() {
        return LDate;
    }

    public void setLDate(int LDate) {
        this.LDate = LDate;
    }

    public double getLRate() {
        return LRate;
    }

    public void setLRate(float LRate) {
        this.LRate = LRate;
    }

    public int getDateType() {
        return DateType;
    }

    public void setDateType(int DType) {
        this.DateType = DType;
    }

    public int getRateType() {
        return RateType;
    }

    public void setRateType(int RateType) {
        this.RateType = RateType;
    }

    public int getRepayType() {
        return repayType;
    }

    public void setRepayType(int repayType) {
        this.repayType = repayType;
    }
}
