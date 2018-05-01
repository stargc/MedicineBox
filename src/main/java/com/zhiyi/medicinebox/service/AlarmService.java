package com.zhiyi.medicinebox.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.Alarm;

@Service("alarmService")
public class AlarmService {

	@Resource
	private BaseDAO<Alarm> baseDAO;
	
	public boolean add(Alarm alarm) {
		if (alarm != null) {
			return baseDAO.save(alarm);
		}
		return false;
	}
	
	public boolean delete(Alarm alarm) {
		if (alarm != null && alarm.getAlarmId() != 0) {
			return baseDAO.delete(alarm);
		}
		return false;
	}
	
	public Alarm findByid(Alarm alarm){
		List<Alarm> list = baseDAO.find("from Alarm a where a.alarmId = ?", new Object[] {alarm.getAlarmId()});
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		return null;
	}
	
	public boolean updateStatus(Alarm alarm){
		StringBuffer sql = new StringBuffer("UPDATE alarm set statusId = ")
				.append(alarm.getStatusId())
				.append(" where alarmid = ")
				.append(alarm.getAlarmId());
		Integer num = baseDAO.excuteBySql(sql.toString());
		if (num != null && num > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateIsSend(Alarm alarm){
		StringBuffer sql = new StringBuffer("UPDATE alarm set issend = ")
				.append(alarm.getIssend())
				.append(" where alarmid = ")
				.append(alarm.getAlarmId());
		Integer num = baseDAO.excuteBySql(sql.toString());
		if (num != null && num > 0) {
			return true;
		}
		return false;
	}
}
