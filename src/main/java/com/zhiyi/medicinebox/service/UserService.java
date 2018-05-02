package com.zhiyi.medicinebox.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.User;


@Service("userService")
public class UserService {
	@Resource
	private BaseDAO<User> baseDAO;
	
	/**
	 * @explain 添加用户
	 * @date   2016年10月31日 下午4:34:26
	 * @return  
	 */
	public boolean add(User user) {
		if(user != null){
			user.setCreateDate(new Date());
			return baseDAO.save(user);
		}
		return false;
	}
	
	public List<User> findByOpenId(String openId) {
		List<User> list = new ArrayList<User>();
		// 时间为null,获取数据库中这台设备最新的消息
		list = baseDAO.find("from User a where a.openId = ?",
				new Object[] { openId });
		return list;
	}
	
	public boolean delete(User o) {
		if (o != null && o.getUserId() != 0) {
			return baseDAO.delete(o);
		}
		return false;
	}
	
	public boolean update(User o){
		if (o != null && o.getUserId() != 0) {
			return baseDAO.update(o);
		}
		return false;
	}
	
}
