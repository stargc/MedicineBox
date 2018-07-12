package com.zhiyi.medicinebox.service.base;

import com.zhiyi.medicinebox.dao.DosageMapper;
import com.zhiyi.medicinebox.entity.base.Dosage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class DosageService {

	@Resource
	private DosageMapper mapper;
	
	public boolean add(Dosage dosage) {
		if (dosage != null) {
			dosage.setCreatedate(new Date());
			return mapper.insertSelective(dosage) > 0;
		}
		return false;
	}
	
	public boolean delete(Dosage dosage) {
		if (dosage != null && dosage.getDosageid() != 0) {
			return mapper.deleteByPrimaryKey(dosage.getDosageid()) > 0;
		}
		return false;
	}
	
	public boolean update(Dosage dosage){
		if (dosage != null && dosage.getDosageid() != 0) {
			return mapper.updateByPrimaryKeySelective(dosage) > 0;
		}
		return false;
	}
}
