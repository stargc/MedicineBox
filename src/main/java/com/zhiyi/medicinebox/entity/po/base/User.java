package com.zhiyi.medicinebox.entity.po.base;

import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    private String password;

    private String openId;

    private String nickName;

    private String avatarUrl;

    private String wxCity;

    private String wxName;

    private Integer wxAge;

    private Integer wxSex;

    private String wxPhone;

    private Date createDate;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public String getWxCity() {
        return wxCity;
    }

    public void setWxCity(String wxCity) {
        this.wxCity = wxCity == null ? null : wxCity.trim();
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName == null ? null : wxName.trim();
    }

    public Integer getWxAge() {
        return wxAge;
    }

    public void setWxAge(Integer wxAge) {
        this.wxAge = wxAge;
    }

    public Integer getWxSex() {
        return wxSex;
    }

    public void setWxSex(Integer wxSex) {
        this.wxSex = wxSex;
    }

    public String getWxPhone() {
        return wxPhone;
    }

    public void setWxPhone(String wxPhone) {
        this.wxPhone = wxPhone == null ? null : wxPhone.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}