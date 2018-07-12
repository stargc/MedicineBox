package com.zhiyi.medicinebox.dao;

import com.zhiyi.medicinebox.entity.base.StatusMed;

public interface StatusMedMapper {
    int deleteByPrimaryKey(Integer statusid);

    int insertSelective(StatusMed record);

    StatusMed selectByPrimaryKey(Integer statusid);

    int updateByPrimaryKeySelective(StatusMed record);
}