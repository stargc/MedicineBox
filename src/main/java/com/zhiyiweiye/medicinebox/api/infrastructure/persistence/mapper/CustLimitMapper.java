package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.CustLimit;

public interface CustLimitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustLimit record);

    int insertSelective(CustLimit record);

    CustLimit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustLimit record);

    int updateByPrimaryKey(CustLimit record);
}