package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.HyVipUser;

public interface HyVipUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HyVipUser record);

    int insertSelective(HyVipUser record);

    HyVipUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HyVipUser record);

    int updateByPrimaryKey(HyVipUser record);

    HyVipUser selectByUserName(String userName);
}