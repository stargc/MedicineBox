package com.zhiyi.medicinebox.api.business.service.base;

import com.zhiyi.medicinebox.api.infrastructure.persistence.mapper.MedicineMapper;
import com.zhiyi.medicinebox.api.infrastructure.persistence.po.Medicine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MedicineService {

	@Resource
	private MedicineMapper mapper;
	
	public boolean add(Medicine medicine) {
		if (medicine != null) {
			medicine.setCreateDate(new Date());
			return mapper.insertSelective(medicine) > 0;
		}
		return false;
	}
	
	public boolean delete(Medicine medicine) {
		if (medicine != null && medicine.getMedId() != 0) {
			return mapper.deleteByPrimaryKey(medicine.getMedId()) > 0;
		}
		return false;
	}
	
	public boolean update(Medicine medicine){
		if (medicine != null && medicine.getMedId() != 0) {
			return mapper.updateByPrimaryKeySelective(medicine) > 0;
		}
		return false;
	}
	
	public Medicine findById(int medId) {
		return mapper.selectByPrimaryKey(medId);
	}
}
