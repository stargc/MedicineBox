package com.zhiyi.medicinebox.entity.alarm;

import java.util.Date;

public class Alarm {
    private Integer alarmid;

    private Integer dosageid;

    private Integer userid;

    private Integer statusid;

    private Date alarmdate;

    private Date alarmtime;

    private Integer alarmlock;

    private Short issend;

    private Date createdate;

    public Integer getAlarmid() {
        return alarmid;
    }

    public void setAlarmid(Integer alarmid) {
        this.alarmid = alarmid;
    }

    public Integer getDosageid() {
        return dosageid;
    }

    public void setDosageid(Integer dosageid) {
        this.dosageid = dosageid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public Date getAlarmdate() {
        return alarmdate;
    }

    public void setAlarmdate(Date alarmdate) {
        this.alarmdate = alarmdate;
    }

    public Date getAlarmtime() {
        return alarmtime;
    }

    public void setAlarmtime(Date alarmtime) {
        this.alarmtime = alarmtime;
    }

    public Integer getAlarmlock() {
        return alarmlock;
    }

    public void setAlarmlock(Integer alarmlock) {
        this.alarmlock = alarmlock;
    }

    public Short getIssend() {
        return issend;
    }

    public void setIssend(Short issend) {
        this.issend = issend;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}