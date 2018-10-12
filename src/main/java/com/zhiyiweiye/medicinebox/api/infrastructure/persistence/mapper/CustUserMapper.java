package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.CustUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CustUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustUser record);

    int insertSelective(CustUser record);

    CustUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustUser record);

    int updateByPrimaryKey(CustUser record);

    CustUser selectByUserName(String username);

    List<CustUser> selectLoginedUser(@Param("username") String username, @Param("loginToken") String loginToken,  @Param("loginTokenTime") Date loginTokenTime);

    int updateLoginToken(@Param("username") String userName, @Param("loginToken") String loginToken, @Param("loginTokenTime") Date loginTokenTime);

    CustUser selectByToken(String loginToken);
}