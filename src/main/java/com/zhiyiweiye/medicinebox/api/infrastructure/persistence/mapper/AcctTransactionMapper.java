package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.AcctTransaction;

public interface AcctTransactionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AcctTransaction record);

    int insertSelective(AcctTransaction record);

    AcctTransaction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AcctTransaction record);

    int updateByPrimaryKey(AcctTransaction record);
}