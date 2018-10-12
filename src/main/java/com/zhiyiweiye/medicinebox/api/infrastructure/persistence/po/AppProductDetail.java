package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po;

import java.util.Date;

public class AppProductDetail {
    private Integer id;

    private Integer appproductid;

    private Integer enablecreditstatus;

    private String productdetailurl;

    private Integer iscredit;

    private Integer minamount;

    private Integer maxamount;

    private String buttontext;

    private String amountarray;

    private String periodunit;

    private String agreementname;

    private String agreementdesfront;

    private String agreementurl;

    private Date creattime;

    private String creatuser;

    private Date updatetime;

    private String updateuser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppproductid() {
        return appproductid;
    }

    public void setAppproductid(Integer appproductid) {
        this.appproductid = appproductid;
    }

    public Integer getEnablecreditstatus() {
        return enablecreditstatus;
    }

    public void setEnablecreditstatus(Integer enablecreditstatus) {
        this.enablecreditstatus = enablecreditstatus;
    }

    public String getProductdetailurl() {
        return productdetailurl;
    }

    public void setProductdetailurl(String productdetailurl) {
        this.productdetailurl = productdetailurl == null ? null : productdetailurl.trim();
    }

    public Integer getIscredit() {
        return iscredit;
    }

    public void setIscredit(Integer iscredit) {
        this.iscredit = iscredit;
    }

    public Integer getMinamount() {
        return minamount;
    }

    public void setMinamount(Integer minamount) {
        this.minamount = minamount;
    }

    public Integer getMaxamount() {
        return maxamount;
    }

    public void setMaxamount(Integer maxamount) {
        this.maxamount = maxamount;
    }

    public String getButtontext() {
        return buttontext;
    }

    public void setButtontext(String buttontext) {
        this.buttontext = buttontext == null ? null : buttontext.trim();
    }

    public String getAmountarray() {
        return amountarray;
    }

    public void setAmountarray(String amountarray) {
        this.amountarray = amountarray == null ? null : amountarray.trim();
    }

    public String getPeriodunit() {
        return periodunit;
    }

    public void setPeriodunit(String periodunit) {
        this.periodunit = periodunit == null ? null : periodunit.trim();
    }

    public String getAgreementname() {
        return agreementname;
    }

    public void setAgreementname(String agreementname) {
        this.agreementname = agreementname == null ? null : agreementname.trim();
    }

    public String getAgreementdesfront() {
        return agreementdesfront;
    }

    public void setAgreementdesfront(String agreementdesfront) {
        this.agreementdesfront = agreementdesfront == null ? null : agreementdesfront.trim();
    }

    public String getAgreementurl() {
        return agreementurl;
    }

    public void setAgreementurl(String agreementurl) {
        this.agreementurl = agreementurl == null ? null : agreementurl.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public String getCreatuser() {
        return creatuser;
    }

    public void setCreatuser(String creatuser) {
        this.creatuser = creatuser == null ? null : creatuser.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }
}