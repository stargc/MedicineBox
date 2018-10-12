package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po;

public class CustUserAuthority {
    private Integer userId;

    private Integer authorityId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }
}