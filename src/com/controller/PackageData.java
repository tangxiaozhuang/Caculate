package com.controller;

import com.bean.CacularBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

/**
 * Created by thz on 2017/4/26.
 */
public class PackageData extends ActionSupport implements ModelDriven<CacularBean> {


    private CacularBean cacularBean = new CacularBean();
    private  String result;
   @Override
    public CacularBean getModel () {
        return cacularBean;
    }


    public CacularBean getCacularBean() {
        return cacularBean;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result=result;
    }

    public void setCacularBean(CacularBean cacularBean) {
        this.cacularBean = cacularBean;
    }


    public String caculator() throws Exception {

        result=JSONArray.fromObject(CaculatorCtr.cacular(cacularBean)).toString();
        ServletActionContext.getResponse().getWriter().print(result);

        return NONE;
    }


}