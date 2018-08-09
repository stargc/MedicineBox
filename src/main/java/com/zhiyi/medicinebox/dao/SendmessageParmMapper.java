package com.zhiyi.medicinebox.dao;

import com.zhiyi.medicinebox.entity.po.sendmsg.SendmessageParm;
import com.zhiyi.medicinebox.entity.vo.sendmsg.WXSendEatMedParmBean;
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