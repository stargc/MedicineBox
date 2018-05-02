package com.zhiyi.medicinebox.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.Medicine;
import com.zhiyi.medicinebox.entity.ViewAlarm;

@Service("MedicineService")
public class MedicineService {

	@Resource
	private BaseDAO<Medicine> baseDAO;
	
	public boolean add(Medicine o) {
		if (o != null) {
			o.setCreateDate(new Date());
			return baseDAO.save(o);
		}
		return false;
	}
	
	public boolean delete(Medicine o) {
		if (o != null && o.getMedId() != 0) {
			return baseDAO.delete(o);
		}
		return false;
	}
	
	public boolean update(Medicine o){
		if (o != null && o.getMedId() != 0) {
			return baseDAO.update(o);
		}
		return false;
	}
	
	public Medicine findById(int medId) {
		List<Medicine> list = baseDAO.find("from Medicine v where v.medId = ?", new Object[] {medId});
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
}
