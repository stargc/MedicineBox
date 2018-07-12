package com.zhiyi.medicinebox.dao;

import com.zhiyi.medicinebox.entity.base.Box;

public interface BoxMapper {
    int deleteByPrimaryKey(Integer boxid);

    int insertSelective(Box record);

    Box selectByPrimaryKey(Integer boxid);

    int updateByPrimaryKeySelective(Box record);
}