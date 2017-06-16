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
 *  月还息到期还本
 还款计算公式：
 •	期限计算方式只有按月计算， 期号数为月数n
 •	每月支付利息为：10000*(m%/12)
 此外，最后一个月还需支付本金，所以，最后一个月的还款计算方式为：还款利息10000(m%/12)+本金10000*

 */
public class CalclnMonthFine implements Caculate {
    public  ArrayList<ResultBean> oparate(CacularBean cacularBean){

        ArrayList<ResultBean> list=new ArrayList<ResultBean>();
        Date fromDate= CacularBean.getDateBg();

        //每期还款时间
        Date nextTime;
        Calendar c1=Calendar.getInstance();
        c1.setTime(fromDate);
        nextTime=c1.getTime();


        double interest;

        for(int k=1;k<=cacularBean.getLDate();k++){
            ResultBean result=new ResultBean();//获取结果实体类对象

            //利率按年算。每月利息=本金*年利率/12
            if (cacularBean.getRateType() == Constant.ANNUALRATE) {
                interest = cacularBean.getLMoney() * (cacularBean.getLRate() / 100) / 12;
            }
            //利率按天算，每月利息=本金*日利率*30
            else
                interest = cacularBean.getLMoney() * (cacularBean.getLRate() /100)*30;


            // 利息
            interest = new BigDecimal(String.valueOf(interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            result.setInterest(interest);

            result.setIssue(k+"/"+cacularBean.getLDate());
            //日期
            c1.add(Calendar.MONTH,1);
            nextTime = c1.getTime();
            result.setPayDate(nextTime);
            //总款项
            if(k==cacularBean.getLDate()){
                double balance=new BigDecimal(String.valueOf(interest+cacularBean.getLMoney())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                result.setBalance(balance);
            }
            else
                result.setBalance(interest);

            list.add(result);//返回结果列表

        }
//
        return list;
    }
}
