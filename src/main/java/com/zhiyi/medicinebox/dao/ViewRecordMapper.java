package com.zhiyi.medicinebox.dao;

import com.zhiyi.medicinebox.entity.alarm.ViewRecord;

import java.util.List;

public interface ViewRecordMapper {
    List<ViewRecord> selectByUserId(Integer userId);
}