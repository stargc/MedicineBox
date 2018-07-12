package com.zhiyi.medicinebox.dao;


import com.zhiyi.medicinebox.entity.alarm.ViewAlarm;

import java.util.List;

public interface ViewAlarmMapper {
    List<ViewAlarm> findViewAlarmByUserId(int userid);

    ViewAlarm findViewAlarmByAlarmId(int alarmid);
}