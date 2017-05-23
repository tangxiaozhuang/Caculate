package com.caculator;

import java.util.Calendar;
import java.util.Date;
/**
 * Created by LRM on 2017/5/7.
 */

/**
*日期运算
 */
public class DateValue {
	private Date date;

	public DateValue(Date date){
		this.date=date;
	}
	//某年天数
	public int AnnualDay(){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year=c.get(Calendar.YEAR);
		if (year%4==0&&year%100!=0||year%400==0)
			return 366;
		else
			return 365;

	}
	//某月天数
	public int ValueOfMonthDay(){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(c.DAY_OF_MONTH);
	}
	//月份数值
	public int ValueOfMonth(){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH)+1;
	}
	//
	public int ValueOfYear(){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

}
