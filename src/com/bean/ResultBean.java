package com.bean;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by thz on 2017/4/25.
 */
public class ResultBean {
    private String issue;
    private double balance;
    private double principal;
    private double interest;
    private double reward;
    private String payDate;

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward=reward;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balan) {
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        df.format(balan);
        balance = balan;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double ints) {
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
        df.format(ints);
        interest=ints;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(Date Date) {
        Calendar c = Calendar.getInstance();
        c.setTime(Date);
        int month=c.get(Calendar.MONTH)+1;
        int year=c.get(Calendar.YEAR);
        int day=c.get(Calendar.DATE);
        payDate=(year+"-"+month+"-"+day);
    }
}

