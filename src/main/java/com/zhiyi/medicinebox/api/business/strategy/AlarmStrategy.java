package com.zhiyi.medicinebox.api.business.strategy;

import com.zhiyi.medicinebox.api.business.common.constant.Consts;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.AlarmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.MedicineMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.RecordMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.ViewAlarmMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Alarm;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Medicine;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Record;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewAlarm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * @author guanchen
 * @version $Id AlarmStrategy.java, v 0.1 2018-07-19 17:49 star Exp $$
 */
@Service
public class AlarmStrategy {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    private AlarmMapper alarmMapper;

    @Autowired
    private MedicineMapper medicineMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ViewAlarmMapper viewAlarmMapper;


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
        if (medicineMapper.insertSelective(medicine) > 0) {
            alarm.setMedId(medicine.getMedId());
            int days = Long.valueOf((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24).intValue() + 1;
            logger.info("用户 " + alarm.getUserId() + "添加记录 " + days + "条");
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
            isDone = alarmMapper.insertSelective(alarm) > 0;
            for (int i = 1; i < days; i++) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                alarm.setAlarmTime(calendar.getTime());
                isDone = alarmMapper.insertSelective(alarm) > 0;
            }
        }
        return isDone;
    }

    /***
     * 用户针对一个用药提醒 服药操作
     * @param alarmId
     * @param statusId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean takeMedicine(int alarmId, int statusId) {
        Alarm alarm = new Alarm();
        alarm.setAlarmId(alarmId);
        alarm.setStatusId(statusId);
        Integer num = alarmMapper.updateByPrimaryKeySelective(alarm);

        if (num != null && num > 0) {
            ViewAlarm newAlarm = viewAlarmMapper.findViewAlarmByAlarmId(alarmId);
            if (newAlarm != null) {
                Record record = getRecordFromAlarm(newAlarm, new Date());
                record.setType(Consts.TAKE_MED_STATUS_EATED);
                return recordMapper.insertSelective(record) > 0;
            }
        }
        return false;
    }

    /***
     * 用户针对一个用药提醒 跳过
     * @param alarmId
     * @param statusId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean skipMedicine(int alarmId, int statusId){
        Alarm alarm = new Alarm();
        alarm.setAlarmId(alarmId);
        alarm.setStatusId(statusId);
        Integer num = alarmMapper.updateByPrimaryKeySelective(alarm);

        if (num != null && num > 0) {
            ViewAlarm newAlarm = viewAlarmMapper.findViewAlarmByAlarmId(alarmId);
            if (newAlarm != null) {
                Record record = getRecordFromAlarm(newAlarm, new Date());
                record.setType(Consts.TAKE_MED_STATUS_SKIP);
                return recordMapper.insertSelective(record) > 0;
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
