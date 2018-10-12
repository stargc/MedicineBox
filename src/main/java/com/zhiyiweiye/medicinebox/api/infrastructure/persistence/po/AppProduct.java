package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po;

import java.util.Date;

public class AppProduct {
    private Integer id;

    private Integer status;

    private Integer ordernum;

    private Integer type;

    private Integer pid;

    private String industrycode;

    private String name;

    private Integer showstatus;

    private Integer checkstatus;

    private String checkmsg;

    private String imgurl;

    private String title;

    private String simplecontent1;

    private String simplecontent2;

    private String limitdescribe;

    private String merproid;

    private String supplierid;

    private String salesiteid;

    private Date creattime;

    private String creatuser;

    private Date updatetime;

    private String updateuser;

    private String perioddesc;

    private String proshowimg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIndustrycode() {
        return industrycode;
    }

    public void setIndustrycode(String industrycode) {
        this.industrycode = industrycode == null ? null : industrycode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getShowstatus() {
        return showstatus;
    }

    public void setShowstatus(Integer showstatus) {
        this.showstatus = showstatus;
    }

    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }

    public String getCheckmsg() {
        return checkmsg;
    }

    public void setCheckmsg(String checkmsg) {
        this.checkmsg = checkmsg == null ? null : checkmsg.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSimplecontent1() {
        return simplecontent1;
    }

    public void setSimplecontent1(String simplecontent1) {
        this.simplecontent1 = simplecontent1 == null ? null : simplecontent1.trim();
    }

    public String getSimplecontent2() {
        return simplecontent2;
    }

    public void setSimplecontent2(String simplecontent2) {
        this.simplecontent2 = simplecontent2 == null ? null : simplecontent2.trim();
    }

    public String getLimitdescribe() {
        return limitdescribe;
    }

    public void setLimitdescribe(String limitdescribe) {
        this.limitdescribe = limitdescribe == null ? null : limitdescribe.trim();
    }

    public String getMerproid() {
        return merproid;
    }

    public void setMerproid(String merproid) {
        this.merproid = merproid == null ? null : merproid.trim();
    }

    public String getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid == null ? null : supplierid.trim();
    }

    public String getSalesiteid() {
        return salesiteid;
    }

    public void setSalesiteid(String salesiteid) {
        this.salesiteid = salesiteid == null ? null : salesiteid.trim();
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

    public String getPerioddesc() {
        return perioddesc;
    }

    public void setPerioddesc(String perioddesc) {
        this.perioddesc = perioddesc == null ? null : perioddesc.trim();
    }

    public String getProshowimg() {
        return proshowimg;
    }

    public void setProshowimg(String proshowimg) {
        this.proshowimg = proshowimg == null ? null : proshowimg.trim();
    }
}