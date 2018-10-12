package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.AppProductDetail;

public interface AppProductDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppProductDetail record);

    int insertSelective(AppProductDetail record);

    AppProductDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppProductDetail record);

    int updateByPrimaryKey(AppProductDetail record);

    AppProductDetail selectByIndustryCode(String industryCode);
}