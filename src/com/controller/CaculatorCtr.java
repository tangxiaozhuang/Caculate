package com.controller;

import com.bean.CacularBean;
import com.bean.ResultBean;
import com.caculator.*;
import com.caculator.caculateImpl.*;

import java.util.List;

/**
 * Created by thz on 2017/5/18.
 */
public class CaculatorCtr {
    public static List<ResultBean> cacular(CacularBean cacularBean){

        int k=cacularBean.getRepayType();
        List<ResultBean> result=null;
        Caculate caculate;
        switch (k){
            case 0:
                caculate= new CalclnMonthFine();
                break;
            case 1:
                caculate= new CalclnFine();
                break;
            case 2:
                caculate= new CalclnDayFine();
                break;
            case 3:
                caculate= new CalclnEqualBlance();
                break;
            case 4:
                caculate= new CalclnMonFiSeaPrin();
                break;
            case 5:
                caculate= new CalclnAssertFine();
                break;
            case 6:
                caculate= new CallnEqualFinePrincipal();
                break;
            case 7:
                caculate= new CalclnEqualPrncipal();
                break;
            case 8:
                caculate= new CalclnSeaFiAsserPrin();
                break;
            default:
                caculate=null;
                break;
        }

        return caculate.oparate(cacularBean);
    }

    public static void main(){

    }
}

