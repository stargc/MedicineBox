package com.zhiyi.medicinebox.dao;


import com.zhiyi.medicinebox.entity.po.base.Medicine;

public interface MedicineMapper {
    int deleteByPrimaryKey(Integer medId);

    int insert(Medicine record);

    int insertSelective(Medicine record);

    Medicine selectByPrimaryKey(Integer medId);

    int updateByPrimaryKeySelective(Medicine record);

    int updateByPrimaryKey(Medicine record);
}