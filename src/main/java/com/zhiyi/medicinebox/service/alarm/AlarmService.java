package com.zhiyi.medicinebox.service.alarm;

import com.zhiyi.medicinebox.dao.AlarmMapper;
import com.zhiyi.medicinebox.dao.ViewAlarmMapper;
import com.zhiyi.medicinebox.entity.po.alarm.Alarm;
import com.zhiyi.medicinebox.entity.po.alarm.ViewAlarm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AlarmService {

	@Resource
	private AlarmMapper mapper;

	@Resource
	private ViewAlarmMapper viewAlarmMapper;

	public boolean add(Alarm alarm) {
		if (alarm != null) {
			alarm.setCreateDate(new Date());
			int num = mapper.insertSelective(alarm);
			if (num > 0){
				return true;
			}
		}
		return false;
	}
	
	public boolean delete(Alarm alarm) {
		if (alarm != null && alarm.getAlarmId() != 0) {
			int num = mapper.deleteByPrimaryKey(alarm.getAlarmId() );
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
		alarm.setAlarmId(alarmId);
		alarm.setStatusId(statusId);
		Integer num = mapper.updateByPrimaryKeySelective(alarm);
		return (num != null && num > 0);
	}
	
	public boolean updateIsSend(Integer alarmId, Short isSend){
		Alarm alarm = new Alarm();
		alarm.setAlarmId(alarmId);
		alarm.setIsSend(isSend);
		Integer num = mapper.updateByPrimaryKeySelective(alarm);
		return (num != null && num > 0);
	}

	public List<ViewAlarm> findViewAlarmByUserId(int userId, Date date){
		return viewAlarmMapper.findViewAlarmByUserId(userId);
	}

	public ViewAlarm findViewAlarmById(int id) {
		return viewAlarmMapper.findViewAlarmByAlarmId(id);
	}



	public List<ViewAlarm> findAlarmToSendMsg(Date startTime, Date endTime){
		return viewAlarmMapper.findAlarmToSendMsg(startTime,endTime);
	}
}
