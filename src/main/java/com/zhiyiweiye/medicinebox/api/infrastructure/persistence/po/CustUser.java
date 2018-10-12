package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po;

import java.math.BigDecimal;
import java.util.Date;

public class CustUser {
    private Integer id;

    private String username;

    private String nickName;

    private String password;

    private String salt;

    private String emailAddress;

    private String emailValidateStr;

    private Date emailValidateStrValidTime;

    private String mobileValidateCode;

    private Date mobileValidateCodeValidTime;

    private Integer certificationScore;

    private String certificationLevel;

    private Integer creditScore;

    private String creditLevel;

    private Date batchTime;

    private Date createTime;

    private String enabled;

    private Integer locked;

    private String mobileno;

    private Date lastloginTime;

    private String passwordEmailValidateStr;

    private Date passwordEmailValidateStrValidTime;

    private String token;

    private Integer infoCompletePercent;

    private Integer certyPercent;

    private Integer infoScore;

    private String inviter;

    private String memberLevel;

    private String createUser;

    private String registeStatus;

    private String custInvcode;

    private String platformMark;

    private String saleInvcode;

    private Date updateDate;

    private String updateUser;

    private String custinvCode;

    private String saleinvCode;

    private BigDecimal inverstTotalamount;

    private Date lastInverstDate;

    private String fenqRegistercode;

    private BigDecimal balance;

    private Long income;

    private Long recharge;

    private String amountValidateCode;

    private String amountValidateCodeTime;

    private String amountValidateTempMobile;

    private String loginToken;

    private String pageIndex;

    private String isgetcredits;

    private String isactivate;

    private Boolean appFlag;

    private String accountValCode;

    private String accountValMobile;

    private String accountValTime;

    private Integer isOldUser;

    private String registerFrom;

    private String custFrom;

    private String loginCity;

    private String reference;

    private Integer referenceUserNum;

    private BigDecimal referenceAmount;

    private Integer doubleSales;

    private Integer isLottery;

    private Integer lotteryNum;

    private String inviteCode;

    private Date loginTokenTime;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }

    public String getEmailValidateStr() {
        return emailValidateStr;
    }

    public void setEmailValidateStr(String emailValidateStr) {
        this.emailValidateStr = emailValidateStr == null ? null : emailValidateStr.trim();
    }

    public Date getEmailValidateStrValidTime() {
        return emailValidateStrValidTime;
    }

    public void setEmailValidateStrValidTime(Date emailValidateStrValidTime) {
        this.emailValidateStrValidTime = emailValidateStrValidTime;
    }

    public String getMobileValidateCode() {
        return mobileValidateCode;
    }

    public void setMobileValidateCode(String mobileValidateCode) {
        this.mobileValidateCode = mobileValidateCode == null ? null : mobileValidateCode.trim();
    }

    public Date getMobileValidateCodeValidTime() {
        return mobileValidateCodeValidTime;
    }

    public void setMobileValidateCodeValidTime(Date mobileValidateCodeValidTime) {
        this.mobileValidateCodeValidTime = mobileValidateCodeValidTime;
    }

    public Integer getCertificationScore() {
        return certificationScore;
    }

    public void setCertificationScore(Integer certificationScore) {
        this.certificationScore = certificationScore;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel == null ? null : certificationLevel.trim();
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public String getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel == null ? null : creditLevel.trim();
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

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled == null ? null : enabled.trim();
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno == null ? null : mobileno.trim();
    }

    public Date getLastloginTime() {
        return lastloginTime;
    }

    public void setLastloginTime(Date lastloginTime) {
        this.lastloginTime = lastloginTime;
    }

    public String getPasswordEmailValidateStr() {
        return passwordEmailValidateStr;
    }

    public void setPasswordEmailValidateStr(String passwordEmailValidateStr) {
        this.passwordEmailValidateStr = passwordEmailValidateStr == null ? null : passwordEmailValidateStr.trim();
    }

    public Date getPasswordEmailValidateStrValidTime() {
        return passwordEmailValidateStrValidTime;
    }

    public void setPasswordEmailValidateStrValidTime(Date passwordEmailValidateStrValidTime) {
        this.passwordEmailValidateStrValidTime = passwordEmailValidateStrValidTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Integer getInfoCompletePercent() {
        return infoCompletePercent;
    }

    public void setInfoCompletePercent(Integer infoCompletePercent) {
        this.infoCompletePercent = infoCompletePercent;
    }

    public Integer getCertyPercent() {
        return certyPercent;
    }

    public void setCertyPercent(Integer certyPercent) {
        this.certyPercent = certyPercent;
    }

    public Integer getInfoScore() {
        return infoScore;
    }

    public void setInfoScore(Integer infoScore) {
        this.infoScore = infoScore;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter == null ? null : inviter.trim();
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel == null ? null : memberLevel.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getRegisteStatus() {
        return registeStatus;
    }

    public void setRegisteStatus(String registeStatus) {
        this.registeStatus = registeStatus == null ? null : registeStatus.trim();
    }

    public String getCustInvcode() {
        return custInvcode;
    }

    public void setCustInvcode(String custInvcode) {
        this.custInvcode = custInvcode == null ? null : custInvcode.trim();
    }

    public String getPlatformMark() {
        return platformMark;
    }

    public void setPlatformMark(String platformMark) {
        this.platformMark = platformMark == null ? null : platformMark.trim();
    }

    public String getSaleInvcode() {
        return saleInvcode;
    }

    public void setSaleInvcode(String saleInvcode) {
        this.saleInvcode = saleInvcode == null ? null : saleInvcode.trim();
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

    public String getCustinvCode() {
        return custinvCode;
    }

    public void setCustinvCode(String custinvCode) {
        this.custinvCode = custinvCode == null ? null : custinvCode.trim();
    }

    public String getSaleinvCode() {
        return saleinvCode;
    }

    public void setSaleinvCode(String saleinvCode) {
        this.saleinvCode = saleinvCode == null ? null : saleinvCode.trim();
    }

    public BigDecimal getInverstTotalamount() {
        return inverstTotalamount;
    }

    public void setInverstTotalamount(BigDecimal inverstTotalamount) {
        this.inverstTotalamount = inverstTotalamount;
    }

    public Date getLastInverstDate() {
        return lastInverstDate;
    }

    public void setLastInverstDate(Date lastInverstDate) {
        this.lastInverstDate = lastInverstDate;
    }

    public String getFenqRegistercode() {
        return fenqRegistercode;
    }

    public void setFenqRegistercode(String fenqRegistercode) {
        this.fenqRegistercode = fenqRegistercode == null ? null : fenqRegistercode.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Long getRecharge() {
        return recharge;
    }

    public void setRecharge(Long recharge) {
        this.recharge = recharge;
    }

    public String getAmountValidateCode() {
        return amountValidateCode;
    }

    public void setAmountValidateCode(String amountValidateCode) {
        this.amountValidateCode = amountValidateCode == null ? null : amountValidateCode.trim();
    }

    public String getAmountValidateCodeTime() {
        return amountValidateCodeTime;
    }

    public void setAmountValidateCodeTime(String amountValidateCodeTime) {
        this.amountValidateCodeTime = amountValidateCodeTime == null ? null : amountValidateCodeTime.trim();
    }

    public String getAmountValidateTempMobile() {
        return amountValidateTempMobile;
    }

    public void setAmountValidateTempMobile(String amountValidateTempMobile) {
        this.amountValidateTempMobile = amountValidateTempMobile == null ? null : amountValidateTempMobile.trim();
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken == null ? null : loginToken.trim();
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex == null ? null : pageIndex.trim();
    }

    public String getIsgetcredits() {
        return isgetcredits;
    }

    public void setIsgetcredits(String isgetcredits) {
        this.isgetcredits = isgetcredits == null ? null : isgetcredits.trim();
    }

    public String getIsactivate() {
        return isactivate;
    }

    public void setIsactivate(String isactivate) {
        this.isactivate = isactivate == null ? null : isactivate.trim();
    }

    public Boolean getAppFlag() {
        return appFlag;
    }

    public void setAppFlag(Boolean appFlag) {
        this.appFlag = appFlag;
    }

    public String getAccountValCode() {
        return accountValCode;
    }

    public void setAccountValCode(String accountValCode) {
        this.accountValCode = accountValCode == null ? null : accountValCode.trim();
    }

    public String getAccountValMobile() {
        return accountValMobile;
    }

    public void setAccountValMobile(String accountValMobile) {
        this.accountValMobile = accountValMobile == null ? null : accountValMobile.trim();
    }

    public String getAccountValTime() {
        return accountValTime;
    }

    public void setAccountValTime(String accountValTime) {
        this.accountValTime = accountValTime == null ? null : accountValTime.trim();
    }

    public Integer getIsOldUser() {
        return isOldUser;
    }

    public void setIsOldUser(Integer isOldUser) {
        this.isOldUser = isOldUser;
    }

    public String getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(String registerFrom) {
        this.registerFrom = registerFrom == null ? null : registerFrom.trim();
    }

    public String getCustFrom() {
        return custFrom;
    }

    public void setCustFrom(String custFrom) {
        this.custFrom = custFrom == null ? null : custFrom.trim();
    }

    public String getLoginCity() {
        return loginCity;
    }

    public void setLoginCity(String loginCity) {
        this.loginCity = loginCity == null ? null : loginCity.trim();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
    }

    public Integer getReferenceUserNum() {
        return referenceUserNum;
    }

    public void setReferenceUserNum(Integer referenceUserNum) {
        this.referenceUserNum = referenceUserNum;
    }

    public BigDecimal getReferenceAmount() {
        return referenceAmount;
    }

    public void setReferenceAmount(BigDecimal referenceAmount) {
        this.referenceAmount = referenceAmount;
    }

    public Integer getDoubleSales() {
        return doubleSales;
    }

    public void setDoubleSales(Integer doubleSales) {
        this.doubleSales = doubleSales;
    }

    public Integer getIsLottery() {
        return isLottery;
    }

    public void setIsLottery(Integer isLottery) {
        this.isLottery = isLottery;
    }

    public Integer getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(Integer lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public Date getLoginTokenTime() {
        return loginTokenTime;
    }

    public void setLoginTokenTime(Date loginTokenTime) {
        this.loginTokenTime = loginTokenTime;
    }
}