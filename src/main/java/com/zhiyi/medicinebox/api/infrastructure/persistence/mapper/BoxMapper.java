package com.zhiyi.medicinebox.api.infrastructure.persistence.mapper;


import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Box;

public interface BoxMapper {
    int deleteByPrimaryKey(Integer boxId);

    int insert(Box record);

    int insertSelective(Box record);

    Box selectByPrimaryKey(Integer boxId);

    int updateByPrimaryKeySelective(Box record);

    int updateByPrimaryKeyWithBLOBs(Box record);

    int updateByPrimaryKey(Box record);
}