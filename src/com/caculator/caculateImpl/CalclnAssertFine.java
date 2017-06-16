package com.caculator.caculateImpl;

/**
 * 标满赋息
 * Created by thz on 2017/5/18.
 */


import com.bean.CacularBean;
import com.bean.ResultBean;
import com.caculator.Caculate;
import com.caculator.domain.Constant;
import com.caculator.domain.DateValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by LRM on 2017/5/7.
 */

/**
 * 标满付息，到期还本
 还款计算公式：
 *分为两期
 •	首月付息为：本金10000m%/12月份
 •	最后一个月本金全额退还：本金10000

 */
public class CalclnAssertFine implements Caculate {
    public ArrayList<ResultBean> oparate(CacularBean cacularBean){
        //获取利息
        double interest;
        //每期还款时间
        Date fromDate= CacularBean.getDateBg();
        ArrayList<ResultBean> scheduleList = new ArrayList<ResultBean>();

        //获取年份的日数
        DateValue date = new DateValue(fromDate);
        //还款日期
        Date nextTime;
        Calendar c1=Calendar.getInstance();
        if(cacularBean.getDateType()== Constant.DAILY){
            c1.add(Calendar.DAY_OF_MONTH,cacularBean.getLDate());
        }
        else {
            c1.add(Calendar.MONTH, cacularBean.getLDate());
        }
        nextTime=c1.getTime();


        //利率按年算,期限按月利息=本金*年利率/12*月数
        if (cacularBean.getDateType()== Constant.MONTHLY&&cacularBean.getRateType() == Constant.ANNUALRATE) {
            interest = cacularBean.getLMoney() * (cacularBean.getLRate()/100) / 12*cacularBean.getLDate();
        }
        //利率按天算，利息=本金*日利率*30*月数
        else if(cacularBean.getDateType()== Constant.MONTHLY&&cacularBean.getRateType() == Constant.DAILYRATE) {
            interest = cacularBean.getLMoney() * (cacularBean.getLRate() / 100) * 30 * cacularBean.getLDate();
        }

        else if(cacularBean.getDateType()== Constant.DAILY&&cacularBean.getRateType() == Constant.DAILYRATE) {
            interest = cacularBean.getLMoney() * (cacularBean.getLRate() / 100) * cacularBean.getLDate();
        }
        else{
            interest=cacularBean.getLMoney()*cacularBean.getLRate()/(100*360)*cacularBean.getLDate();
        }

        interest = new BigDecimal(String.valueOf(interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        for(int i=1;i<=2;i++){
           ResultBean resultBean = new ResultBean();
            if(i==1){
                resultBean.setInterest(interest);
                resultBean.setBalance(interest);
                resultBean.setPayDate(fromDate);
            }
            else {
                resultBean.setPrincipal(cacularBean.getLMoney());
                resultBean.setPayDate(nextTime);
                resultBean.setBalance(cacularBean.getLMoney());
            }
            resultBean.setIssue(i+"/"+2);
            scheduleList.add(resultBean);
        }


        return scheduleList;
    }

}

