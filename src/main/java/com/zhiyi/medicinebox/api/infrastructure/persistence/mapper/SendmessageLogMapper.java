package com.zhiyi.medicinebox.api.infrastructure.persistence.mapper;


import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageLog;

public interface SendmessageLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SendmessageLog record);

    int insertSelective(SendmessageLog record);

    SendmessageLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SendmessageLog record);

    int updateByPrimaryKey(SendmessageLog record);
}