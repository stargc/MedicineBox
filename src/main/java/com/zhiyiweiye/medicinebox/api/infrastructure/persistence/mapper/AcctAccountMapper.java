package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.AcctAccount;

public interface AcctAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AcctAccount record);

    int insertSelective(AcctAccount record);

    AcctAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AcctAccount record);

    int updateByPrimaryKey(AcctAccount record);
}