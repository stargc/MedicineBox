package com.zhiyi.medicinebox.dao;

import com.zhiyi.medicinebox.entity.alarm.Alarm;

public interface AlarmMapper {
    int deleteByPrimaryKey(Integer alarmid);

    int insertSelective(Alarm record);

    Alarm selectByPrimaryKey(Integer alarmid);

    int updateByPrimaryKeySelective(Alarm record);

}