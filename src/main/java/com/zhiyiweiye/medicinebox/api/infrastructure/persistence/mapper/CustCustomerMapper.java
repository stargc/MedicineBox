package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.CustCustomer;
import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.CustCustomerWithBLOBs;
import org.apache.ibatis.annotations.Param;

public interface CustCustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustCustomerWithBLOBs record);

    int insertSelective(CustCustomerWithBLOBs record);

    CustCustomerWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustCustomerWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CustCustomerWithBLOBs record);

    int updateByPrimaryKey(CustCustomer record);

    CustCustomer selectByUserName(String username);

    int selectOneInfo(@Param("idNo") String idNo, @Param("mobile") String mobile);
}