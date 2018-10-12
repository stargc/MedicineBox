package com.zhiyi.medicinebox.api.infrastructure.persistence.mapper;


import com.zhiyi.medicinebox.api.infrastructure.persistence.po.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    User selectByOpenId(String openid);
}