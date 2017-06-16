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
 * /**
 * 按日算息，每月付息，到期还本
 还款计算公式：
 •	固定还款日 number_repayment，默认值0为借出日期日数值位
 •	期限计算方式有按月计算和结束日期计算，结束日期为空，按月份数值计算，结束日期不为空，按结束日期计算
 •	期限计算方式按月和按结束日期计算过程相同,期号数为n
 首月还款利息为：10000(m％/（闰年？366：365）)*（放款当日到首月还款日的实际天数）
 中间月份的还款计算方式为：10000(m％/（闰年？366：365）)*（每月实际天数）
 最后一月的还款利息为10000(m％/（闰年？366：365）)*（上个还款日到指定还款截止日期的实际天数 ）
 此外，最后一个月还需支付本金，所以，最后一个月的还款计算方式为：还款利息+本金（10000元）

 */

public class CalclnDayFine implements Caculate {
    public  ArrayList<ResultBean> oparate(CacularBean cacularBean){
        //获取利息
        double interest;
        //每期还款时间
        Date fromDate=CacularBean.getDateBg();
        ArrayList<ResultBean> scheduleList = new ArrayList<ResultBean>();



        //获取利息
        //每期还款时间
        Date nextTime;
        Calendar c1=Calendar.getInstance();
        c1.setTime(fromDate);
        nextTime=c1.getTime();




        //按月计算
        if(cacularBean.getDateType()== Constant.MONTHLY) {
            for (int i = 1; i <= cacularBean.getLDate(); i++) {

                ResultBean resultBean = new ResultBean();

                DateValue date =new DateValue(nextTime);


                //利率按年算。每月利息=本金*年利率/年天数*月天数
                if (cacularBean.getRateType() == Constant.ANNUALRATE) {
                    interest = cacularBean.getLMoney() * (cacularBean.getLRate() /100)/ date.AnnualDay() * date.ValueOfMonthDay();
                }
                //利率按天算，每月利息=本金*日利率*月天数
                else
                    interest = cacularBean.getLMoney() * (cacularBean.getLRate() /100)*date.ValueOfMonthDay();

                interest = new BigDecimal(String.valueOf(interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                //跨年跨月处理


                if (i == cacularBean.getLDate()) {
                    //最后一个月，需要加上本金

                    resultBean.setBalance(interest + cacularBean.getLMoney());
                    resultBean.setPrincipal(cacularBean.getLMoney());

                } else {
                    resultBean.setBalance(interest);
                }


                c1.add(Calendar.MONTH,1);

                nextTime = c1.getTime();

                resultBean.setIssue(i+"/"+cacularBean.getLDate());
                resultBean.setBalance(interest);
                resultBean.setInterest(interest);
                resultBean.setPayDate(nextTime);
                scheduleList.add(resultBean);
            }
        }
        else{
            return null;
        }
        return scheduleList;
    }
}
