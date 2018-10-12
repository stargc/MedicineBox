package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po;

import java.util.Date;

public class CustUserExtrasInfo {
    private Integer id;

    private Integer userId;

    private String userName;

    private String mobileMd5;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getMobileMd5() {
        return mobileMd5;
    }

    public void setMobileMd5(String mobileMd5) {
        this.mobileMd5 = mobileMd5 == null ? null : mobileMd5.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}