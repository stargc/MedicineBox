package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.CustUserExtrasInfo;

public interface CustUserExtrasInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustUserExtrasInfo record);

    int insertSelective(CustUserExtrasInfo record);

    CustUserExtrasInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustUserExtrasInfo record);

    int updateByPrimaryKey(CustUserExtrasInfo record);

    int selectCountByMobileMd5(String mobileMd5);
}