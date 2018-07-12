package com.zhiyi.medicinebox.entity.base;

import java.util.Date;

public class Dosage {
    private Integer dosageid;

    private Integer medid;

    private String dosage;

    private Integer statusid;

    private Date createdate;

    public Integer getDosageid() {
        return dosageid;
    }

    public void setDosageid(Integer dosageid) {
        this.dosageid = dosageid;
    }

    public Integer getMedid() {
        return medid;
    }

    public void setMedid(Integer medid) {
        this.medid = medid;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage == null ? null : dosage.trim();
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}