package com.caculator;

import com.bean.CacularBean;
import com.bean.ResultBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by thz on 2017/5/18.
 *  * 按月还本息（等额本息）
 还款计算公式：
 *期限计算方式只有按月计算
 * 每月月供额=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
 * 每月应还利息=剩余资金*月利率
 * 每月还本金=月供额-应还利息
 */
public class CalclnEqualBlance {

    public  static ArrayList<ResultBean> oparate(CacularBean cacularBean){
        //获取利息
        double interest;
        //每期还款时间
        Date fromDate=CacularBean.getDateBg();

        ArrayList<ResultBean> scheduleList = new ArrayList<ResultBean>();




        //计算月利率
        double MonthRate;
        if(cacularBean.getRateType()==Constant.ANNUALRATE) {
            MonthRate=cacularBean.getLRate()/(12*100);
        }
        else
            MonthRate=cacularBean.getLRate()*30/100;


        //复合利率
        double Complex=Math.pow(1+MonthRate,cacularBean.getLDate());

        //每月等额待收
        double MonthBalence=cacularBean.getLMoney()*MonthRate*Complex/(Complex-1);

        MonthBalence=new BigDecimal(String.valueOf(MonthBalence)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();



        //每期还款时间
        Date nextTime;
        Calendar c1=Calendar.getInstance();
        c1.setTime(fromDate);
        nextTime=c1.getTime();

        for (int i = 1; i <= cacularBean.getLDate(); i++) {

            ResultBean resultBean = new ResultBean();


            //利率按年算。每月利息=本金*年利率/12
            if(cacularBean.getRateType()==Constant.ANNUALRATE) {
                interest = cacularBean.getLMoney() * cacularBean.getLRate()/(12*100);
            }
            //利率按天算，每月利息=本金*日利率*30
            else
                interest=cacularBean.getLMoney()*cacularBean.getLRate()*30/100;


            interest = new BigDecimal(String.valueOf(interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();


            //每月待收本金
            double principal=MonthBalence-interest;
            principal = new BigDecimal(String.valueOf(principal)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            resultBean.setPrincipal(principal);

            resultBean.setIssue(i+"/"+cacularBean.getLDate());
            //每月利息

            //每月等额待收
            resultBean.setBalance(MonthBalence);

            //计算剩余资金
            double remainMoney=cacularBean.getLMoney()-principal;
            remainMoney = new BigDecimal(String.valueOf(remainMoney)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();


            c1.add(Calendar.MONTH,1);

            nextTime = c1.getTime();
            resultBean.setPayDate(nextTime);
            resultBean.setInterest(interest);
            cacularBean.setLMoney(remainMoney);

            scheduleList.add(resultBean);
        }
        return scheduleList;

    }
}
