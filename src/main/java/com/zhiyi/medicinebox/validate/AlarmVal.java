package com.zhiyi.medicinebox.validate;

import com.zhiyi.medicinebox.entity.vo.alarm.AlarmAddReq;
import org.springframework.stereotype.Service;

/**
 * @author guanchen
 * @version $Id AlarmVal.java, v 0.1 2018-07-25 14:38 star Exp $$
 */

@Service
public class AlarmVal {

    public String addVal(AlarmAddReq alarmAddReq) {
        if (alarmAddReq == null) {
            return "参数内容为空";
        }
        if (alarmAddReq.getMedName() == null) {
            return "药品信息不能为空";
        }
        if (alarmAddReq.getDosage() == null) {
            return "药品用量不能为空";
        }
        if (alarmAddReq.getAlarmTime() == null) {
            return "药品提醒时间不能为空";
        }
        if (alarmAddReq.getUserId() == 0) {
            return "用户信息不能为空";
        }
        if (alarmAddReq.getStartDate() == null) {
            return "药品提醒开始时间不能为空";
        }
        if (alarmAddReq.getEndDate() == null) {
            return "药品提醒结束时间不能为空";
        }
        return null;
    }
}
