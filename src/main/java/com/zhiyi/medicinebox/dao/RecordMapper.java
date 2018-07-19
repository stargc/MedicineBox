package com.zhiyi.medicinebox.dao;

import com.zhiyi.medicinebox.entity.po.alarm.Record;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(Record record);
}