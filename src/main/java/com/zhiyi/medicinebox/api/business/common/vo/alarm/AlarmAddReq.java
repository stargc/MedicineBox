package com.zhiyi.medicinebox.api.business.common.vo.alarm;

import lombok.Data;

import java.util.Date;

/**
 * @author guanchen
 * @version $Id AlarmAddReq.java, v 0.1 2018-07-19 15:43 star Exp $$
 */
@Data
public class AlarmAddReq {
    private String medName;
    private String dosage;
    private Date alarmTime;
    private Date startDate;
    private Date endDate;
    private Integer userId;
    private Integer statusId;
}
