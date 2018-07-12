package com.zhiyi.medicinebox.service.alarm;

import com.zhiyi.medicinebox.dao.AlarmMapper;
import com.zhiyi.medicinebox.dao.ViewAlarmMapper;
import com.zhiyi.medicinebox.entity.alarm.Alarm;
import com.zhiyi.medicinebox.entity.alarm.ViewAlarm;
import com.zhiyi.medicinebox.entity.base.Dosage;
import com.zhiyi.medicinebox.entity.base.Medicine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AlarmService {

	@Resource
	private AlarmMapper mapper;

	@Resource
	private ViewAlarmMapper viewMapper;

	public boolean add(Alarm alarm) {
		if (alarm != null) {
			alarm.setCreatedate(new Date());
			int num = mapper.insertSelective(alarm);
			if (num > 0){
				return true;
			}
		}
		return false;
	}
	
	public boolean delete(Alarm alarm) {
		if (alarm != null && alarm.getAlarmid() != 0) {
			int num = mapper.deleteByPrimaryKey(alarm.getAlarmid() );
			if (num > 0){
				return true;
			}
		}
		return false;
	}
	
	public Alarm findByid(Integer alarmId){
		return mapper.selectByPrimaryKey(alarmId);
	}
	
	public boolean updateStatus(Integer alarmId, Integer statusId){
		Alarm alarm = new Alarm();
		alarm.setAlarmid(alarmId);
		alarm.setStatusid(statusId);
		Integer num = mapper.updateByPrimaryKeySelective(alarm);
		return (num != null && num > 0);
	}
	
	public boolean updateIsSend(Integer alarmId, Short isSend){
		Alarm alarm = new Alarm();
		alarm.setAlarmid(alarmId);
		alarm.setIssend(isSend);
		Integer num = mapper.updateByPrimaryKeySelective(alarm);
		return (num != null && num > 0);
	}

	public List<ViewAlarm> findViewAlarmByUserId(int userId, Date date){
		return viewMapper.findViewAlarmByUserId(userId);
	}

	public ViewAlarm findViewAlarmById(int id) {
		return viewMapper.findViewAlarmByAlarmId(id);
	}

	public String addVal(Alarm alarm, Dosage dosage, Medicine medicine, Date startDate, Date endDate){
		if (medicine == null || (medicine != null && medicine.getMedname() == null)){
			return "药品信息不能为空";
		}
		if (dosage == null|| (dosage != null && dosage.getDosage() == null)){
			return "药品用量不能为空";
		}
		if (alarm == null){
			return "药品提醒信息不能为空";
		}
		if (alarm != null && alarm.getAlarmtime() == null){
			return "药品提醒时间不能为空";
		}
		if (alarm != null && alarm.getUserid() == 0){
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
