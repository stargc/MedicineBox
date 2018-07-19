package com.zhiyi.medicinebox.dao;


import com.zhiyi.medicinebox.entity.po.base.Box;

public interface BoxMapper {
    int deleteByPrimaryKey(Integer boxId);

    int insert(Box record);

    int insertSelective(Box record);

    Box selectByPrimaryKey(Integer boxId);

    int updateByPrimaryKeySelective(Box record);

    int updateByPrimaryKeyWithBLOBs(Box record);

    int updateByPrimaryKey(Box record);
}