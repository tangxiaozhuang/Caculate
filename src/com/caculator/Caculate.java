package com.caculator;

import com.bean.CacularBean;
import com.bean.ResultBean;

import java.util.ArrayList;

/**
 * Created by thz on 2017/6/16.
 */
public interface Caculate {
    public ArrayList<ResultBean> oparate(CacularBean cacularBean);
}
