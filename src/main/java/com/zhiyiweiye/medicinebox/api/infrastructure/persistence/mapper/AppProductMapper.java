package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.AppProduct;

public interface AppProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppProduct record);

    int insertSelective(AppProduct record);

    AppProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppProduct record);

    int updateByPrimaryKey(AppProduct record);
}