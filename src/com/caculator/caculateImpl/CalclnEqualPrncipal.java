package com.caculator.caculateImpl;

import com.bean.CacularBean;
import com.bean.ResultBean;
import com.caculator.Caculate;
import com.caculator.domain.Constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by thz on 2017/5/18.
 *  * 等额本金
 *等额本金：是指一种贷款的还款方式，是在还款期内把贷款数总额等分，每月偿还同等数额的本金和剩余贷款在该月所产生的利息，这样由于每月的还款本金额固定，而利息越来越少，借款人起初还款压力较大，但是随时间的推移每月还款数也越来越少。

 *等额本金计算公式：每月还款金额 = （贷款本金 / 还款月数） + （本金 — 已归还本金累计额） × 每月利率

 */
public class CalclnEqualPrncipal implements Caculate {

    public  ArrayList<ResultBean> oparate(CacularBean cacularBean){
        //获取利息
        double interest;
        //每期还款时间
        Date fromDate=CacularBean.getDateBg();
        ArrayList<ResultBean> scheduleList = new ArrayList<ResultBean>();


        //每月1还本金
        double prin=cacularBean.getLMoney()/cacularBean.getLDate();
        prin = new BigDecimal(String.valueOf(prin)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        //每期还款时间
        Date nextTime;
        Calendar c1=Calendar.getInstance();
        c1.setTime(fromDate);

        for (int i = 1; i <= cacularBean.getLDate(); i++) {

            ResultBean resultBean = new ResultBean();



            //利率按年算。每月利息=本金*年利率/12
            if(cacularBean.getRateType()== Constant.ANNUALRATE) {
                interest = cacularBean.getLMoney() * (cacularBean.getLRate()/100)/12;
            }
            //利率按天算，每月利息=本金*日利率*30
            else
                interest=cacularBean.getLMoney()*(cacularBean.getLRate()/100)*30;
            interest = new BigDecimal(String.valueOf(interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();


            //结算余额
            double remainMoney =new BigDecimal(String.valueOf(cacularBean.getLMoney()-prin)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            cacularBean.setLMoney(remainMoney);


            c1.add(Calendar.MONTH,1);
            nextTime = c1.getTime();
            resultBean.setIssue(i+"/"+cacularBean.getLDate());
            resultBean.setInterest(interest);
            resultBean.setPayDate(nextTime);
            resultBean.setPrincipal(prin);

            double balance=new BigDecimal(String.valueOf(prin+interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            resultBean.setBalance(balance);


            scheduleList.add(resultBean);

        }
        return scheduleList;
    }
}
