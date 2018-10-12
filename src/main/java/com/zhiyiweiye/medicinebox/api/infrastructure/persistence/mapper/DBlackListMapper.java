package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.DBlackList;

public interface DBlackListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DBlackList record);

    int insertSelective(DBlackList record);

    DBlackList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DBlackList record);

    int updateByPrimaryKey(DBlackList record);

    int selectCountByMD5Mobile(String mobileno);
}