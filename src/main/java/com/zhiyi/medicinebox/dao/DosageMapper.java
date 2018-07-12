package com.zhiyi.medicinebox.dao;

import com.zhiyi.medicinebox.entity.base.Dosage;

public interface DosageMapper {
    int deleteByPrimaryKey(Integer dosageid);

    int insertSelective(Dosage record);

    Dosage selectByPrimaryKey(Integer dosageid);

    int updateByPrimaryKeySelective(Dosage record);
}