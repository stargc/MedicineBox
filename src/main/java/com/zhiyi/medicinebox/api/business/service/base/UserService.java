package com.zhiyi.medicinebox.api.business.service.base;

import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.UserMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.User;
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
			user.setCreateDate(new Date());
			return mapper.insertSelective(user) > 0;
		}
		return false;
	}
	
	public User findByOpenId(String openId) {
		return mapper.selectByOpenId(openId);
	}
	
	public boolean delete(User user) {
		if (user != null && user.getUserId() != 0) {
			return mapper.deleteByPrimaryKey(user.getUserId()) > 0;
		}
		return false;
	}
	
	public boolean update(User user){
		if (user != null && user.getUserId() != 0) {
			return mapper.updateByPrimaryKeySelective(user) > 0;
		}
		return false;
	}

	public User findByUserId(Integer userid) {
		return mapper.selectByPrimaryKey(userid);
	}
	
}
