package com.zhiyi.medicinebox.service.base;

import com.zhiyi.medicinebox.dao.MedicineMapper;
import com.zhiyi.medicinebox.entity.base.Medicine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MedicineService {

	@Resource
	private MedicineMapper mapper;
	
	public boolean add(Medicine medicine) {
		if (medicine != null) {
			medicine.setCreatedate(new Date());
			return mapper.insertSelective(medicine) > 0;
		}
		return false;
	}
	
	public boolean delete(Medicine medicine) {
		if (medicine != null && medicine.getMedid() != 0) {
			return mapper.deleteByPrimaryKey(medicine.getMedid()) > 0;
		}
		return false;
	}
	
	public boolean update(Medicine medicine){
		if (medicine != null && medicine.getMedid() != 0) {
			return mapper.updateByPrimaryKeySelective(medicine) > 0;
		}
		return false;
	}
	
	public Medicine findById(int medId) {
		return mapper.selectByPrimaryKey(medId);
	}
}
