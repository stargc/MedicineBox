package com.zhiyi.medicinebox.api.business.service.alarm.add;

import com.zhiyi.medicinebox.api.business.common.validator.ValidationResult;
import com.zhiyi.medicinebox.api.business.common.validator.Validator;
import org.springframework.stereotype.Service;

/**
 * @author guanchen
 * @version $Id AlarmVal.java, v 0.1 2018-07-25 14:38 star Exp $$
 */

@Service
public class AlarmAddVal implements Validator<AlarmAddReq> {

    @Override
    public ValidationResult validate(AlarmAddReq alarmAddReq) {

        ValidationResult validationResult = new ValidationResult();
        validationResult.setValid(false);
        validationResult.setErrorMessage("");

        if (alarmAddReq == null) {
            validationResult.setErrorMessage("参数内容为空");
            return validationResult;
        }
        if (alarmAddReq.getMedName() == null) {
            validationResult.setErrorMessage("药品信息不能为空");
            return validationResult;
        }
        if (alarmAddReq.getDosage() == null) {
            validationResult.setErrorMessage("药品用量不能为空");
            return validationResult;
        }
        if (alarmAddReq.getAlarmTime() == null) {
            validationResult.setErrorMessage("药品提醒时间不能为空");
            return validationResult;
        }
        if (alarmAddReq.getUserId() == 0) {
            validationResult.setErrorMessage("用户信息不能为空");
            return validationResult;
        }
        if (alarmAddReq.getStartDate() == null) {
            validationResult.setErrorMessage("药品提醒开始时间不能为空");
            return validationResult;
        }
        if (alarmAddReq.getEndDate() == null) {
            validationResult.setErrorMessage("药品提醒结束时间不能为空");
            return validationResult;
        }
        if (Long.compare(alarmAddReq.getStartDate().getTime(),alarmAddReq.getEndDate().getTime()) > 0) {
            validationResult.setErrorMessage("提醒开始日期需小于结束日期");
            return validationResult;
        }
        validationResult.setValid(true);
        return validationResult;
    }
}
