package com.zhiyi.medicinebox.dao;

import com.zhiyi.medicinebox.entity.base.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    User selectByOpenId(String openid);
}