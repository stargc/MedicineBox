package com.zhiyi.medicinebox.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.Dosage;

@Service("DosageService")
public class DosageService {

	@Resource
	private BaseDAO<Dosage> baseDAO;
	
	public boolean add(Dosage o) {
		if (o != null) {
			return baseDAO.save(o);
		}
		return false;
	}
	
	public boolean delete(Dosage o) {
		if (o != null && o.getDosageId() != 0) {
			return baseDAO.delete(o);
		}
		return false;
	}
	
	public boolean update(Dosage o){
		if (o != null && o.getDosageId() != 0) {
			return baseDAO.update(o);
		}
		return false;
	}
}
