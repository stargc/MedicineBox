package com.zhiyi.medicinebox.api.business.strategy;

import com.zhiyi.medicinebox.api.business.common.constant.Consts;
import com.zhiyi.medicinebox.api.business.service.alarm.AlarmService;
import com.zhiyi.medicinebox.api.business.service.alarm.RecordService;
import com.zhiyi.medicinebox.api.business.service.base.MedicineService;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Alarm;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Medicine;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Record;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewAlarm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

/**
 * @author guanchen
 * @version $Id AlarmStrategy.java, v 0.1 2018-07-19 17:49 star Exp $$
 */
@Service
public class AlarmStrategy {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Resource
    private AlarmService alarmService;
    @Resource
    private MedicineService medicineService;
    @Resource
    private RecordService recordService;
    @Autowired
    private HttpServletRequest request;

    /****
     * 添加一套用药提醒
     * @param medicine
     * @param alarm
     * @param startDate
     * @param endDate
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addViewAlarm(Medicine medicine, Alarm alarm, Date startDate, Date endDate) {
        boolean isDone = false;
        if (medicineService.add(medicine)) {
            alarm.setMedId(medicine.getMedId());
            int days = Long.valueOf((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24).intValue() + 1;
            logger.info("【" + request.getAttribute("UUID") + "】- 用户 " + alarm.getUserId() + "添加记录 " + days + "条");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            if (alarm.getStatusId() == null || 0 == alarm.getStatusId()) {
                //默认状态为 未服药 状态
                alarm.setStatusId(1);
            }
            calendar.set(Calendar.HOUR_OF_DAY, alarm.getAlarmTime().getHours());
            calendar.set(Calendar.MINUTE, alarm.getAlarmTime().getMinutes());
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

    /***
     * 用户针对一个用药提醒 服药操作
     * @param alarmid
     * @param statusid
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean takeMedicine(int alarmid, int statusid) {
        if (alarmService.updateStatus(alarmid, statusid)) {
            ViewAlarm newAlarm = alarmService.findViewAlarmById(alarmid);
            if (newAlarm != null) {
                Record record = getRecordFromAlarm(newAlarm, new Date());
                record.setType(Consts.TAKE_MED_STATUS_EATED);
                return recordService.add(record);
            }
        }
        return false;
    }

    /***
     * 用户针对一个用药提醒 跳过
     * @param alarmid
     * @param statusid
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean skipMedicine(int alarmid, int statusid){
        if(alarmService.updateStatus(alarmid,statusid)){
            ViewAlarm newAlarm = alarmService.findViewAlarmById(alarmid);
            if (newAlarm != null) {
                Record record = getRecordFromAlarm(newAlarm, new Date());
                record.setType(Consts.TAKE_MED_STATUS_SKIP);
                return recordService.add(record);
            }
        }
        return false;
    }

    private Record getRecordFromAlarm(ViewAlarm alarm, Date date) {
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
