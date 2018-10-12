package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po;

import java.math.BigDecimal;
import java.util.Date;

public class CustLimit {
    private Integer id;

    private Date createTime;

    private BigDecimal applyAmount;

    private BigDecimal blockAmount;

    private BigDecimal maxAmount;

    private BigDecimal trustAmount;

    private BigDecimal useAmount;

    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public BigDecimal getBlockAmount() {
        return blockAmount;
    }

    public void setBlockAmount(BigDecimal blockAmount) {
        this.blockAmount = blockAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getTrustAmount() {
        return trustAmount;
    }

    public void setTrustAmount(BigDecimal trustAmount) {
        this.trustAmount = trustAmount;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}