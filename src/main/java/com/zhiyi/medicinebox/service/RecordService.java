package com.zhiyi.medicinebox.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.Record;

@Service("RecordService")
public class RecordService {

	@Resource
	private BaseDAO<Record> baseDAO;
	
	public boolean add(Record o) {
		if (o != null) {
			return baseDAO.save(o);
		}
		return false;
	}
	
	public boolean delete(Record o) {
		if (o != null && o.getRecordId() != 0) {
			return baseDAO.delete(o);
		}
		return false;
	}
	
	public boolean update(Record o){
		if (o != null && o.getRecordId() != 0) {
			return baseDAO.update(o);
		}
		return false;
	}
}
