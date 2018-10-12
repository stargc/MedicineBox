package com.zhiyi.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyi.medicinebox.api.infrastructure.persistence.po.SendmessageParm;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SendmessageParmMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SendmessageParm record);

    SendmessageParm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SendmessageParm record);

    int deleteByDate(@Param("createdate") Date createdate);

    List<SendmessageParm> findByUserId(int userid);

    int scrapParm(@Param("createdate") Date createdate);
}