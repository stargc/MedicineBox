package com.zhiyi.medicinebox.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.Box;

@Service("boxService")
public class BoxService {

	@Resource
	private BaseDAO<Box> baseDAO;
	public boolean add(Box o) {
		if (o != null) {
			return baseDAO.save(o);
		}
		return false;
	}
	
	public boolean delete(Box o) {
		if (o != null && o.getBoxId() != 0) {
			return baseDAO.delete(o);
		}
		return false;
	}
	
	public boolean update(Box o){
		if (o != null && o.getBoxId() != 0) {
			return baseDAO.update(o);
		}
		return false;
	}
}
