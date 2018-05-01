package com.zhiyi.medicinebox.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.UserBox;

@Service("UserBoxService")
public class UserBoxService {

	@Resource
	private BaseDAO<UserBox> baseDAO;
	
	public boolean add(UserBox o) {
		if (o != null) {
			return baseDAO.save(o);
		}
		return false;
	}
	
	public boolean delete(UserBox o) {
		if (o != null && o.getUbId() != 0) {
			return baseDAO.delete(o);
		}
		return false;
	}
	
	public boolean update(UserBox o){
		if (o != null && o.getUbId() != 0) {
			return baseDAO.update(o);
		}
		return false;
	}
}
