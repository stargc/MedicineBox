package com.zhiyi.medicinebox.api.business.service.base;

import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.StatusMedMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.StatusMed;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StatusMedService {

	@Resource
	private StatusMedMapper mapper;
	
	public boolean add(StatusMed statusMed) {
		if (statusMed != null) {
			return mapper.insertSelective(statusMed) > 0;
		}
		return false;
	}
	
	public boolean delete(StatusMed statusMed) {
		if (statusMed != null && statusMed.getStatusId() != 0) {
			return mapper.deleteByPrimaryKey(statusMed.getStatusId()) > 0;
		}
		return false;
	}
	
	public boolean update(StatusMed statusMed){
		if (statusMed != null && statusMed.getStatusId() != 0) {
			return mapper.updateByPrimaryKeySelective(statusMed) > 0;
		}
		return false;
	}
	
	public StatusMed findById(int id){
		return mapper.selectByPrimaryKey(id);
	}
}
