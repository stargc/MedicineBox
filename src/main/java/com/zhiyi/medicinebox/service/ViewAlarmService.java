package com.zhiyi.medicinebox.service;

import com.zhiyi.medicinebox.dao.BaseDAO;
import com.zhiyi.medicinebox.entity.Alarm;
import com.zhiyi.medicinebox.entity.Dosage;
import com.zhiyi.medicinebox.entity.Medicine;
import com.zhiyi.medicinebox.entity.ViewAlarm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public String addVal(Alarm alarm, Dosage dosage, Medicine medicine, Date startDate, Date endDate){
		if (medicine == null || (medicine != null && medicine.getMedName() == null)){
			return "药品信息不能为空";
		}
		if (dosage == null|| (dosage != null && dosage.getDosage() == null)){
			return "药品用量不能为空";
		}
		if (alarm == null){
			return "药品提醒信息不能为空";
		}
		if (alarm != null && alarm.getAlarmTime() == null){
			return "药品提醒时间不能为空";
		}
		if (alarm != null && alarm.getUserId() == 0){
			return "药品提醒userId不能为空";
		}
		if (startDate == null){
			return "药品提醒开始时间不能为空";
		}
		if (endDate == null){
			return "药品提醒结束时间不能为空";
		}
		return null;
	}
}
