package com.caculator.caculateImpl;


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
 * Created by thz on 2017/5/18.
 * * 等本等息
 •	月还款为：本金+利息
 •	月本金还款为 本金/期数
 •	月利息为 本金*m%/12
 •	总利息为本金m%/12期数

 */
public class CallnEqualFinePrincipal implements Caculate {
    public  ArrayList<ResultBean> oparate(CacularBean cacularBean){
        //获取利息
        double interest;
        //每期还款时间
        Date fromDate= CacularBean.getDateBg();
        ArrayList<ResultBean> scheduleList = new ArrayList<ResultBean>();

        //下一次
        Date nextTime;

        DateValue date = new DateValue(fromDate);
        int days = date.ValueOfMonthDay();
        int month = date.ValueOfMonth();
        int year = date.ValueOfYear();

        //每月待收本金
        double principal=cacularBean.getLMoney()/cacularBean.getLDate();
        principal= new BigDecimal(String.valueOf(principal)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        if(cacularBean.getDateType()== Constant.MONTHLY) {
            for (int i = 1; i <= cacularBean.getLDate(); i++) {
                ResultBean resultBean = new ResultBean();
                int issue=cacularBean.getDateType();
                //利率按年算。每月利息=本金*年利率/12
                if(cacularBean.getRateType()==Constant.ANNUALRATE) {
                    interest = cacularBean.getLMoney() * (cacularBean.getLRate()/100)/12;
                }
                //利率按天算，每月利息=本金*日利率*30
                else
                    interest = cacularBean.getLMoney() * (cacularBean.getLRate()/100) * 30;
                interest = new BigDecimal(String.valueOf(interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();



                //每月待收利息
                resultBean.setInterest(interest);
                //跨年跨月处理
                month++;
                if (month > 12) {
                    year++;
                    month = 1;
                }

                resultBean.setPrincipal(principal);

                //每月待收全额balance=待收本金+待收利息
                double balance= new BigDecimal(String.valueOf(interest+principal)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                resultBean.setBalance(balance);

                //还款日期
                Calendar c=Calendar.getInstance();
                c.set(year,month,days);
                nextTime=c.getTime();
                resultBean.setPayDate(nextTime);
                resultBean.setIssue(i+"/"+cacularBean.getLDate());
                scheduleList.add(resultBean);
            }
        }
        else{
            return null;
        }
        return scheduleList;
    }

}
