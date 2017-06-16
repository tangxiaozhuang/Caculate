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
 */
public class CalclnFine implements Caculate {
    public ArrayList<ResultBean> oparate(CacularBean cacularBean){
        ArrayList<ResultBean> list=new ArrayList<ResultBean>();
        //每期还款时间
        Date fromDate=cacularBean.getDateBg();

        Date nextTime;
        Calendar c1=Calendar.getInstance();
        c1.setTime(fromDate);
        nextTime=c1.getTime();

        //获取利息
        double interest;

        DateValue dateValue=new DateValue(nextTime);





        if (cacularBean.getDateType()== Constant.MONTHLY&&cacularBean.getRateType() == Constant.ANNUALRATE) {
            interest=cacularBean.getLMoney()*cacularBean.getLRate()/(100*12)*cacularBean.getLDate();
        }

        else if(cacularBean.getDateType()==Constant.MONTHLY&&cacularBean.getRateType() == Constant.DAILYRATE) {
            interest=cacularBean.getLMoney()*cacularBean.getLRate()*dateValue.AnnualDay()/(12*100)*cacularBean.getLDate();
        }

        else if(cacularBean.getDateType()==Constant.DAILY&&cacularBean.getRateType() == Constant.DAILYRATE) {
            interest=cacularBean.getLMoney()*cacularBean.getLRate()/100*cacularBean.getLDate();

        }
        else{
            interest=cacularBean.getLMoney()*cacularBean.getLRate()/(100*dateValue.AnnualDay())*cacularBean.getLDate();
        }


        interest = new BigDecimal(String.valueOf(interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        ResultBean result=new ResultBean();//获取结果实体类对象

        result.setIssue(1+"/"+1);//赋值期号

        result.setInterest(interest);

        double balance=new BigDecimal(String.valueOf(interest+cacularBean.getLMoney())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        result.setBalance(balance);//赋值利息

        result.setPrincipal(cacularBean.getLMoney());//赋值本金


        if(cacularBean.getDateType()==Constant.DAILY){
            c1.add(Calendar.DAY_OF_MONTH,cacularBean.getLDate());
        }
        else
            c1.add(Calendar.MONTH,cacularBean.getLDate());

        result.setPayDate(nextTime);
        list.add(result);
        return list;
    }

}
