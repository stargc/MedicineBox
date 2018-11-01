package com.zhiyi.medicinebox.api.business.service.alarm.add;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author guanchen
 * @version $Id AlarmAddReq.java, v 0.1 2018-07-19 15:43 star Exp $$
 */
@Data
public class AlarmAddReq {
    private String medName;
    private String dosage;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date alarmTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    private Integer userId;
    private Integer statusId;
}
