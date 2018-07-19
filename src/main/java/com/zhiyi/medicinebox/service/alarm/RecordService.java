package com.zhiyi.medicinebox.service.alarm;

import com.zhiyi.medicinebox.dao.RecordMapper;
import com.zhiyi.medicinebox.dao.ViewRecordMapper;
import com.zhiyi.medicinebox.entity.po.alarm.Record;
import com.zhiyi.medicinebox.entity.po.alarm.ViewRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecordService {

	@Resource
	private RecordMapper mapper;

	@Resource
	private ViewRecordMapper viewMapper;
	
	public boolean add(Record record) {
		if (record != null) {
			return mapper.insertSelective(record) > 0;
		}
		return false;
	}
	
	public boolean delete(Record record) {
		if (record != null && record.getRecordId() != 0) {
			return mapper.deleteByPrimaryKey(record.getRecordId()) > 0;
		}
		return false;
	}
	
	public boolean update(Record record){
		if (record != null && record.getRecordId() != 0) {
			return mapper.updateByPrimaryKeySelective(record) > 0;
		}
		return false;
	}

	public List<ViewRecord> findViewRecordByUserId(int userId, Date date){
		List<ViewRecord> list = new ArrayList<>();
		//and v.alarmDate < ? or (v.alarmDate = ? and v.alarmTime < ?)
		list = viewMapper.selectByUserId(userId);
		return list;
	}
}
