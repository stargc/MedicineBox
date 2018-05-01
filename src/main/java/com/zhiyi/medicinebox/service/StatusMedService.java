package com.zhiyi.medicinebox.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.StatusMed;

@Service("StatusMedService")
public class StatusMedService {

	@Resource
	private BaseDAO<StatusMed> baseDAO;
	
	public boolean add(StatusMed o) {
		if (o != null) {
			return baseDAO.save(o);
		}
		return false;
	}
	
	public boolean delete(StatusMed o) {
		if (o != null && o.getStatusId() != 0) {
			return baseDAO.delete(o);
		}
		return false;
	}
	
	public boolean update(StatusMed o){
		if (o != null && o.getStatusId() != 0) {
			return baseDAO.update(o);
		}
		return false;
	}
	
	public StatusMed findById(int id){
		List<StatusMed> status = new ArrayList<>();
		status = baseDAO.find("from StatusMed s where s.statusId = ?", new Object[]{id});
		if (status != null && status.size() != 0) {
			return status.get(0);
		}
		return null;
	}
}
