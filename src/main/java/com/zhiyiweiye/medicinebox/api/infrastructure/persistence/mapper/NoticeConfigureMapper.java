package com.zhiyiweiye.medicinebox.api.infrastructure.persistence.mapper;

import com.zhiyiweiye.medicinebox.api.infrastructure.persistence.po.NoticeConfigure;

public interface NoticeConfigureMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_configure
     *
     * @mbg.generated Thu Aug 09 14:30:44 CST 2018
     */
    int deleteByPrimaryKey(String bussId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_configure
     *
     * @mbg.generated Thu Aug 09 14:30:44 CST 2018
     */
    int insert(NoticeConfigure record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_configure
     *
     * @mbg.generated Thu Aug 09 14:30:44 CST 2018
     */
    int insertSelective(NoticeConfigure record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_configure
     *
     * @mbg.generated Thu Aug 09 14:30:44 CST 2018
     */
    NoticeConfigure selectByPrimaryKey(String bussId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_configure
     *
     * @mbg.generated Thu Aug 09 14:30:44 CST 2018
     */
    int updateByPrimaryKeySelective(NoticeConfigure record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice_configure
     *
     * @mbg.generated Thu Aug 09 14:30:44 CST 2018
     */
    int updateByPrimaryKey(NoticeConfigure record);
}