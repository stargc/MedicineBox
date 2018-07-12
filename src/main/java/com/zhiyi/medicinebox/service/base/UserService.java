package com.zhiyi.medicinebox.service.base;

import com.zhiyi.medicinebox.dao.UserMapper;
import com.zhiyi.medicinebox.entity.base.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class UserService {
	@Resource
	private UserMapper mapper;
	
	/**
	 * @explain 添加用户
	 * @date   2016年10月31日 下午4:34:26
	 * @return  
	 */
	public boolean add(User user) {
		if(user != null){
			user.setCreatedate(new Date());
			return mapper.insertSelective(user) > 0;
		}
		return false;
	}
	
	public User findByOpenId(String openId) {
		return mapper.selectByOpenId(openId);
	}
	
	public boolean delete(User user) {
		if (user != null && user.getUserid() != 0) {
			return mapper.deleteByPrimaryKey(user.getUserid()) > 0;
		}
		return false;
	}
	
	public boolean update(User user){
		if (user != null && user.getUserid() != 0) {
			return mapper.updateByPrimaryKeySelective(user) > 0;
		}
		return false;
	}
	
}
