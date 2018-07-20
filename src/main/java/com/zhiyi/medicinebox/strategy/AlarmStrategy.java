package com.zhiyi.medicinebox.strategy;

import com.zhiyi.medicinebox.entity.po.alarm.Alarm;
import com.zhiyi.medicinebox.entity.po.alarm.Record;
import com.zhiyi.medicinebox.entity.po.alarm.ViewAlarm;
import com.zhiyi.medicinebox.entity.po.base.Medicine;
import com.zhiyi.medicinebox.entity.vo.alarm.AlarmAddReq;
import com.zhiyi.medicinebox.service.alarm.AlarmService;
import com.zhiyi.medicinebox.service.base.MedicineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * @author guanchen
 * @version $Id AlarmStrategy.java, v 0.1 2018-07-19 17:49 star Exp $$
 */
@Service
public class AlarmStrategy {
    @Resource
    private AlarmService alarmService;
    @Resource
    private MedicineService medicineService;

    @Transactional(rollbackFor = Exception.class)
    public boolean addViewAlarm(Medicine medicine, Alarm alarm, Date startDate, Date endDate) {
        boolean isDone = false;
        if (medicineService.add(medicine)) {
            int days = Long.valueOf((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24).intValue() + 1;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            if (alarm.getStatusId() == null || 0 == alarm.getStatusId()) {
                //默认状态为 未服药 状态
                alarm.setStatusId(1);
            }
            calendar.set(Calendar.HOUR_OF_DAY, alarm.getAlarmTime().getHours());
            calendar.set(Calendar.MINUTE,alarm.getAlarmTime().getMinutes());
            //添加当天记录
            alarm.setAlarmTime(calendar.getTime());
            isDone = alarmService.add(alarm);
            for (int i = 1; i < days; i++) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                alarm.setAlarmTime(calendar.getTime());
                isDone = alarmService.add(alarm);
            }
        }
        return isDone;
    }

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

    public Record getRecordFromAlarm(ViewAlarm alarm, Date date) {
        Record record = new Record();
        record.setAlarmId(alarm.getAlarmId());
        record.setAlarmTime(alarm.getAlarmTime());
        record.setCreateDate(date);
        record.setDosage(alarm.getDosage());
        record.setMedName(alarm.getMedName());
        record.setStatusId(alarm.getStatusId());
        record.setUserId(alarm.getUserId());
        record.setUrl(alarm.getUrl());
        return record;
    }
}
