package com.zhiyi.medicinebox.dao;


import com.zhiyi.medicinebox.entity.po.base.StatusMed;

public interface StatusMedMapper {
    int deleteByPrimaryKey(Integer statusId);

    int insert(StatusMed record);

    int insertSelective(StatusMed record);

    StatusMed selectByPrimaryKey(Integer statusId);

    int updateByPrimaryKeySelective(StatusMed record);

    int updateByPrimaryKey(StatusMed record);
}