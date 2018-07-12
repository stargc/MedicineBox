package com.zhiyi.medicinebox.entity.base;

import java.util.Date;

public class Medicine {
    private Integer medid;

    private String medname;

    private String malady;

    private String instructions;

    private String url;

    private Date createdate;

    public Integer getMedid() {
        return medid;
    }

    public void setMedid(Integer medid) {
        this.medid = medid;
    }

    public String getMedname() {
        return medname;
    }

    public void setMedname(String medname) {
        this.medname = medname == null ? null : medname.trim();
    }

    public String getMalady() {
        return malady;
    }

    public void setMalady(String malady) {
        this.malady = malady == null ? null : malady.trim();
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions == null ? null : instructions.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}