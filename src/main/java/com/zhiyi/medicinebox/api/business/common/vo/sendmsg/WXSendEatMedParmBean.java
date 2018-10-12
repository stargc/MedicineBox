package com.zhiyi.medicinebox.api.business.common.vo.sendmsg;

import lombok.Data;

import java.util.Date;

@Data
public class WXSendEatMedParmBean {

    private Integer alarmId;
    private Date alarmDate;
    private Date alarmTime;
    private Integer statusId;
    private String dosage;
    private String medName;
    private String formId;
    private String prepayId;
    private Integer parmId;
    private String openId;
    private Integer userId;
    private String userName;
    private Integer medId;

}
