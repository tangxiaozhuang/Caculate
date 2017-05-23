package com.controller;

import com.bean.CacularBean;
import com.bean.ResultBean;
import com.caculator.*;

import java.util.List;

/**
 * Created by thz on 2017/5/18.
 */
public class CaculatorCtr {
    public static List<ResultBean> cacular(CacularBean cacularBean){

        int k=cacularBean.getRepayType();
        List<ResultBean> result=null;
        switch (k){
            case 0:
                result= CalclnMonthFine.oparate(cacularBean);
                break;
            case 1:
                result=CalclnFine.oparate(cacularBean);
                break;
            case 2:
                result= CalclnDayFine.oparate(cacularBean);
                break;
            case 3:
                result= CalclnEqualBlance.oparate(cacularBean);
                break;
            case 4:
                result=CalclnMonFiSeaPrin.oparate(cacularBean);
                break;
            case 5:
                result= CalclnAssertFine.oparate(cacularBean);
                break;
            case 6:
                result= CallnEqualFinePrincipal.oparate(cacularBean);
                break;
            case 7:
                result= CalclnEqualPrncipal.oparate(cacularBean);
                break;
            case 8:
                result= CalclnSeaFiAsserPrin.oparate(cacularBean);
                break;
        }


        return result;
    }
}

