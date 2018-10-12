package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.ProductShowSwitch;

import java.util.List;

public interface ProductShowSwitchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductShowSwitch record);

    int insertSelective(ProductShowSwitch record);

    ProductShowSwitch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductShowSwitch record);

    int updateByPrimaryKey(ProductShowSwitch record);

    List<ProductShowSwitch> selectBtIndustryCode(String industryCode);
}