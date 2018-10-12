package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po;

import java.math.BigDecimal;
import java.util.Date;

public class HyVipUser {
    private Integer id;

    private String username;

    private Long amount;

    private String mobile;

    private Integer initPeriod;

    private String status;

    private Integer isVip;

    private Integer isAuth;

    private Integer type;

    private Date createTime;

    private Date updateTime;

    private String message;

    private Integer applyVipd;

    private BigDecimal availableAmount;

    private String adjustAgain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getInitPeriod() {
        return initPeriod;
    }

    public void setInitPeriod(Integer initPeriod) {
        this.initPeriod = initPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Integer getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getApplyVipd() {
        return applyVipd;
    }

    public void setApplyVipd(Integer applyVipd) {
        this.applyVipd = applyVipd;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getAdjustAgain() {
        return adjustAgain;
    }

    public void setAdjustAgain(String adjustAgain) {
        this.adjustAgain = adjustAgain == null ? null : adjustAgain.trim();
    }
}