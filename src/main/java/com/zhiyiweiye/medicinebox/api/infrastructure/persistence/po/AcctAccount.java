package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po;

import java.math.BigDecimal;
import java.util.Date;

public class AcctAccount {
    private Integer id;

    private String username;

    private BigDecimal balance;

    private BigDecimal freezeAmount;

    private BigDecimal debitPrinciple;

    private BigDecimal debitInterest;

    private BigDecimal overDuePrincipleD;

    private BigDecimal overDurInterestD;

    private BigDecimal penaltyD;

    private BigDecimal lateFeeD;

    private BigDecimal breachContractD;

    private BigDecimal overDueAmounD;

    private BigDecimal creditPrinciple;

    private BigDecimal creditInterest;

    private BigDecimal overDuePrincipleC;

    private BigDecimal overDueInterestC;

    private BigDecimal penaltyC;

    private BigDecimal lateFeeC;

    private BigDecimal breachContractC;

    private BigDecimal overDueAmountC;

    private BigDecimal creditLimit;

    private BigDecimal creditLimitBlance;

    private Date batchTime;

    private Date createTime;

    private Integer totalLoanTimes;

    private Integer loanSuccTimes;

    private Long totalLoanAmount;

    private Long preAuthoAmount;

    private Long totalRepayAmount;

    private String accountType;

    private String createUser;

    private Date updateDate;

    private String updateUser;

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getDebitPrinciple() {
        return debitPrinciple;
    }

    public void setDebitPrinciple(BigDecimal debitPrinciple) {
        this.debitPrinciple = debitPrinciple;
    }

    public BigDecimal getDebitInterest() {
        return debitInterest;
    }

    public void setDebitInterest(BigDecimal debitInterest) {
        this.debitInterest = debitInterest;
    }

    public BigDecimal getOverDuePrincipleD() {
        return overDuePrincipleD;
    }

    public void setOverDuePrincipleD(BigDecimal overDuePrincipleD) {
        this.overDuePrincipleD = overDuePrincipleD;
    }

    public BigDecimal getOverDurInterestD() {
        return overDurInterestD;
    }

    public void setOverDurInterestD(BigDecimal overDurInterestD) {
        this.overDurInterestD = overDurInterestD;
    }

    public BigDecimal getPenaltyD() {
        return penaltyD;
    }

    public void setPenaltyD(BigDecimal penaltyD) {
        this.penaltyD = penaltyD;
    }

    public BigDecimal getLateFeeD() {
        return lateFeeD;
    }

    public void setLateFeeD(BigDecimal lateFeeD) {
        this.lateFeeD = lateFeeD;
    }

    public BigDecimal getBreachContractD() {
        return breachContractD;
    }

    public void setBreachContractD(BigDecimal breachContractD) {
        this.breachContractD = breachContractD;
    }

    public BigDecimal getOverDueAmounD() {
        return overDueAmounD;
    }

    public void setOverDueAmounD(BigDecimal overDueAmounD) {
        this.overDueAmounD = overDueAmounD;
    }

    public BigDecimal getCreditPrinciple() {
        return creditPrinciple;
    }

    public void setCreditPrinciple(BigDecimal creditPrinciple) {
        this.creditPrinciple = creditPrinciple;
    }

    public BigDecimal getCreditInterest() {
        return creditInterest;
    }

    public void setCreditInterest(BigDecimal creditInterest) {
        this.creditInterest = creditInterest;
    }

    public BigDecimal getOverDuePrincipleC() {
        return overDuePrincipleC;
    }

    public void setOverDuePrincipleC(BigDecimal overDuePrincipleC) {
        this.overDuePrincipleC = overDuePrincipleC;
    }

    public BigDecimal getOverDueInterestC() {
        return overDueInterestC;
    }

    public void setOverDueInterestC(BigDecimal overDueInterestC) {
        this.overDueInterestC = overDueInterestC;
    }

    public BigDecimal getPenaltyC() {
        return penaltyC;
    }

    public void setPenaltyC(BigDecimal penaltyC) {
        this.penaltyC = penaltyC;
    }

    public BigDecimal getLateFeeC() {
        return lateFeeC;
    }

    public void setLateFeeC(BigDecimal lateFeeC) {
        this.lateFeeC = lateFeeC;
    }

    public BigDecimal getBreachContractC() {
        return breachContractC;
    }

    public void setBreachContractC(BigDecimal breachContractC) {
        this.breachContractC = breachContractC;
    }

    public BigDecimal getOverDueAmountC() {
        return overDueAmountC;
    }

    public void setOverDueAmountC(BigDecimal overDueAmountC) {
        this.overDueAmountC = overDueAmountC;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getCreditLimitBlance() {
        return creditLimitBlance;
    }

    public void setCreditLimitBlance(BigDecimal creditLimitBlance) {
        this.creditLimitBlance = creditLimitBlance;
    }

    public Date getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(Date batchTime) {
        this.batchTime = batchTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTotalLoanTimes() {
        return totalLoanTimes;
    }

    public void setTotalLoanTimes(Integer totalLoanTimes) {
        this.totalLoanTimes = totalLoanTimes;
    }

    public Integer getLoanSuccTimes() {
        return loanSuccTimes;
    }

    public void setLoanSuccTimes(Integer loanSuccTimes) {
        this.loanSuccTimes = loanSuccTimes;
    }

    public Long getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(Long totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public Long getPreAuthoAmount() {
        return preAuthoAmount;
    }

    public void setPreAuthoAmount(Long preAuthoAmount) {
        this.preAuthoAmount = preAuthoAmount;
    }

    public Long getTotalRepayAmount() {
        return totalRepayAmount;
    }

    public void setTotalRepayAmount(Long totalRepayAmount) {
        this.totalRepayAmount = totalRepayAmount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
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
}