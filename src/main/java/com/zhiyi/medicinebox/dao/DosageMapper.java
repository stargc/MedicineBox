package com.zhiyi.medicinebox.dao;


import com.zhiyi.medicinebox.entity.po.base.Dosage;

public interface DosageMapper {
    int deleteByPrimaryKey(Integer dosageId);

    int insert(Dosage record);

    int insertSelective(Dosage record);

    Dosage selectByPrimaryKey(Integer dosageId);

    int updateByPrimaryKeySelective(Dosage record);

    int updateByPrimaryKey(Dosage record);
}