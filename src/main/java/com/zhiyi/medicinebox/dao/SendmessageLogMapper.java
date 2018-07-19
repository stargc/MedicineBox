package com.zhiyi.medicinebox.dao;


import com.zhiyi.medicinebox.entity.po.sendmsg.SendmessageLog;

public interface SendmessageLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SendmessageLog record);

    int insertSelective(SendmessageLog record);

    SendmessageLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SendmessageLog record);

    int updateByPrimaryKey(SendmessageLog record);
}