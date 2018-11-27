package com.zhiyi.medicinebox.api.infrastructure.persistence.mapper;


import com.zhiyi.medicinebox.api.infrastructure.persistence.po.ViewAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ViewAlarmMapper {
    List<ViewAlarm> findViewAlarmByUserId(@Param("userid")int userid,@Param("num")int num, @Param("offset")int offset);

    ViewAlarm findViewAlarmByAlarmId(int alarmid);

    List<ViewAlarm> findAlarmToSendMsg(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}