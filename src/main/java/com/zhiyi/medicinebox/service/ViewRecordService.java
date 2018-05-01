package com.zhiyi.medicinebox.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.ViewRecord;

@Service("ViewRecordService")
public class ViewRecordService {

	@Resource
	private BaseDAO<ViewRecord> baseDAO;
	
	public List<ViewRecord> findByUserId(int userId, Date date){
		List<ViewRecord> list = new ArrayList<>();
		//and v.alarmDate < ? or (v.alarmDate = ? and v.alarmTime < ?)
		list = baseDAO.find("from ViewRecord v where v.userId = ?  ORDER BY v.alarmDate DESC,v.alarmTime DESC", new Object[]{userId});
		return list;
	}
}
