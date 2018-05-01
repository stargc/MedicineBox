package com.zhiyi.medicinebox.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.SendMessageLog;

@Service("SendMessageLogService")
public class SendMessageLogService {

	@Resource
	private BaseDAO<SendMessageLog> baseDAO;
	public boolean add(SendMessageLog o) {
		if (o != null) {
			return baseDAO.save(o);
		}
		return false;
	}
	
	public boolean delete(SendMessageLog o) {
		if (o != null && o.getId() != 0) {
			return baseDAO.delete(o);
		}
		return false;
	}
	
	public boolean update(SendMessageLog o){
		if (o != null && o.getId() != 0) {
			return baseDAO.update(o);
		}
		return false;
	}
}
