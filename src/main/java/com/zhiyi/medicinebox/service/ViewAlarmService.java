package com.zhiyi.medicinebox.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.Alarm;
import com.zhiyi.medicinebox.entity.ViewAlarm;

@Service("ViewAlarmService")
public class ViewAlarmService {

	@Resource
	private BaseDAO<ViewAlarm> baseDAO;

	public List<ViewAlarm> findByUserId(int userId, Date date){
		List<ViewAlarm> list = new ArrayList<>();
		//and v.alarmDate > ? or (v.alarmDate = ? and v.alarmTime > ?)
		list = baseDAO.find("from ViewAlarm v where v.userId = ? and v.statusId = 1  ORDER BY v.alarmDate,v.alarmTime", new Object[]{userId});
		return list;
	}
	
	public ViewAlarm findById(Alarm alarm) {
		List<ViewAlarm> list = baseDAO.find("from ViewAlarm v where v.alarmId = ?", new Object[] {alarm.getAlarmId()});
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	
	public ViewAlarm findById(int id) {
		List<ViewAlarm> list = baseDAO.find("from ViewAlarm v where v.alarmId = ?", new Object[] {id});
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
}
