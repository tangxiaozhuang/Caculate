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
 */
public class CalclnSeaFiAsserPrin implements Caculate {
    public ArrayList<ResultBean> oparate(CacularBean cacularBean){
        ArrayList<ResultBean> scheduleList = new ArrayList<ResultBean>();
        Date fromDate=CacularBean.getDateBg();
        Date nextTime;
        Calendar c1=Calendar.getInstance();
        c1.setTime(fromDate);
        nextTime=c1.getTime();
        double interest;
        int k=(cacularBean.getLDate()+2)/3;
        for(int i=1;i<=k;i++){
            ResultBean resultBean = new ResultBean();

            //利率按年算。每月利息=本金*年利率/12
            if(cacularBean.getRateType()== Constant.ANNUALRATE) {
                interest = cacularBean.getLMoney() *( cacularBean.getLRate()/100)/12*3;
            }
            //利率按天算，每月利息=本金*日利率*30
            else
                interest=cacularBean.getLMoney()*(cacularBean.getLRate()/100)*30*3;
            interest = new BigDecimal(String.valueOf(interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            resultBean.setIssue(i+"/"+k);
            resultBean.setInterest(interest);
            if(i==k){
                double balance=new BigDecimal(String.valueOf(interest+cacularBean.getLMoney())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                resultBean.setBalance(balance);
            }
            else
                resultBean.setBalance(interest);
            c1.add(Calendar.MONTH,3);
            nextTime = c1.getTime();
            resultBean.setPayDate(nextTime);
            scheduleList.add(resultBean);

        }
        return scheduleList;
    }
}
