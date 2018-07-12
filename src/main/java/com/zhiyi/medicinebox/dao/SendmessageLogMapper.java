package com.zhiyi.medicinebox.dao;

import com.zhiyi.medicinebox.entity.sendmsg.SendmessageLog;

public interface SendmessageLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SendmessageLog record);

    SendmessageLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SendmessageLog record);
}