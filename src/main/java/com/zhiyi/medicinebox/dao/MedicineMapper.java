package com.zhiyi.medicinebox.dao;

import com.zhiyi.medicinebox.entity.base.Medicine;

public interface MedicineMapper {
    int deleteByPrimaryKey(Integer medid);

    int insertSelective(Medicine record);

    Medicine selectByPrimaryKey(Integer medid);

    int updateByPrimaryKeySelective(Medicine record);
}