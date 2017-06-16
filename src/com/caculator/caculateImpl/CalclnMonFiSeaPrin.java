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
 *  * 月还息按季还本
 还款计算公式：
 计算方式只能按月，期号数为月份数n 初始化remainingBalance=loanDate
 每三期还本计算：loanDate/((n+2)/3) 待收余额（计算本金）
 每三期 待收余额 （计算本金）remainingBalance=loanDate-loanDate/((n+2)/3)
 每月还息计算：remainingBalance*m%/12 每三期待收款额为remainingBalance*m%/12+loanDate/((n+2)/3) 若为最后一期，收款额为remainingBalance*m%/12+loanDate/((n+2)/3)

 */
public class CalclnMonFiSeaPrin implements Caculate {
    public  ArrayList<ResultBean> oparate(CacularBean cacularBean){
        //获取利息
        double interest;
        //每期还款时间
        Date fromDate= CacularBean.getDateBg();
        ArrayList<ResultBean> scheduleList = new ArrayList<ResultBean>();

        //每期还款时间
        Date nextTime;
        Calendar c1=Calendar.getInstance();
        c1.setTime(fromDate);
        nextTime=c1.getTime();

        //每季还款本金为
        double SeaPrin=cacularBean.getLMoney()/((cacularBean.getLDate()+2)/3);
        SeaPrin = new BigDecimal(String.valueOf(SeaPrin)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        DateValue date=new DateValue(nextTime);
        int days=date.ValueOfMonthDay();
        int month=date.ValueOfMonth();
        int year=date.ValueOfYear();

        for (int i = 1; i <= cacularBean.getLDate(); i++) {
            int issue=cacularBean.getLDate();
            ResultBean resultBean = new ResultBean();



            //利率按年算。每月利息=本金*年利率/12
            if(cacularBean.getRateType()== Constant.ANNUALRATE) {
                interest = cacularBean.getLMoney() *( cacularBean.getLRate()/100)/12;
            }
            //利率按天算，每月利息=本金*日利率*30
            else
                interest=cacularBean.getLMoney()*(cacularBean.getLRate()/100)*30;
            interest = new BigDecimal(String.valueOf(interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            //跨年跨月处理
            month++;
            if (month > 12) {
                year++;
                month = 1;
            }


            //每三期还本计算
            if (i%3==0||i == cacularBean.getLDate()){
                resultBean.setPrincipal(SeaPrin);
                //还本后余额计算
                double remainMoney=cacularBean.getLMoney()-SeaPrin;
                remainMoney= new BigDecimal(String.valueOf(remainMoney)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                cacularBean.setLMoney(remainMoney);
            }
            else{
                resultBean.setPrincipal(0);
            }


            //每月还款利息
            resultBean.setInterest(interest);
            //每期待收款项计算：
            double balance= new BigDecimal(String.valueOf(resultBean.getInterest()+resultBean.getPrincipal())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            resultBean.setBalance(balance);

            //还款日期
            Calendar c=Calendar.getInstance();
            c.set(year,month,days);
            nextTime=c.getTime();
            resultBean.setPayDate(nextTime);
            resultBean.setIssue(i+"/"+issue);
            scheduleList.add(resultBean);

        }

        return scheduleList;
    }

}
