package com.zhiyi.medicinebox.api.infrastructure.persistence.mapper;


import com.zhiyi.medicinebox.api.infrastructure.persistence.po.StatusMed;

public interface StatusMedMapper {
    int deleteByPrimaryKey(Integer statusId);

    int insert(StatusMed record);

    int insertSelective(StatusMed record);

    StatusMed selectByPrimaryKey(Integer statusId);

    int updateByPrimaryKeySelective(StatusMed record);

    int updateByPrimaryKey(StatusMed record);
}