package com.zhiyi.medicinebox.api.business.service.alarm.search;

import com.zhiyi.medicinebox.api.business.common.vo.BaseResponse;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewAlarm;
import lombok.Data;

import java.util.Date;

/**
 * @author guanchen
 * @version $Id AlarmResp.java, v 0.1 2018-10-18 15:41 star Exp $$
 */
@Data
public class AlarmResp extends BaseResponse{
    private Integer alarmId;

    private Date createDate;

    private Date alarmTime;

    private Integer alarmLock;

    private String userName;

    private String medName;

    private String dosage;

    private String status;

    private String url;

    public AlarmResp fillData(ViewAlarm alarm){
        this.setAlarmId(alarm.getAlarmId());
        this.setAlarmLock(alarm.getAlarmLock());
        this.setAlarmTime(alarm.getAlarmTime());
        this.setCreateDate(alarm.getCreateDate());
        this.setDosage(alarm.getDosage());
        this.setMedName(alarm.getMedName());
        this.setStatus(alarm.getStatus());
        this.setUrl(alarm.getUrl());
        this.setUserName(alarm.getUserName());
        return this;
    }
}
