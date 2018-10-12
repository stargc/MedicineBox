package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.CustUserAuthority;
import org.apache.ibatis.annotations.Param;

public interface CustUserAuthorityMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("authorityId") Integer authorityId);

    int insert(CustUserAuthority record);

    int insertSelective(CustUserAuthority record);
}