package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po;

import java.math.BigDecimal;
import java.util.Date;

public class AcctTransaction {
    private Integer id;

    private String tranType;

    private String tranCode;

    private BigDecimal amount;

    private BigDecimal blance;

    private Integer accountId;

    private Integer loanId;

    private String transDesc;

    private Date createTime;

    private String createUser;

    private Date updateDate;

    private String updateUser;

    private Date procDate;

    private String whdNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType == null ? null : tranType.trim();
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode == null ? null : tranCode.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBlance() {
        return blance;
    }

    public void setBlance(BigDecimal blance) {
        this.blance = blance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getTransDesc() {
        return transDesc;
    }

    public void setTransDesc(String transDesc) {
        this.transDesc = transDesc == null ? null : transDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getProcDate() {
        return procDate;
    }

    public void setProcDate(Date procDate) {
        this.procDate = procDate;
    }

    public String getWhdNo() {
        return whdNo;
    }

    public void setWhdNo(String whdNo) {
        this.whdNo = whdNo == null ? null : whdNo.trim();
    }
}